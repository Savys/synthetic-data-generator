package com.salesforce.industries.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesforce.industries.bulk.BulkOperation;
import com.salesforce.industries.core.Consts;
import com.salesforce.industries.core.RandUtil;
import com.salesforce.industries.core.RandomListUtil;
import com.salesforce.industries.persistence.model.CarePlan;
import com.salesforce.industries.pojo.AccountCarePlan;
import com.salesforce.industries.pojo.BulkResult;
import com.salesforce.industries.pojo.CaseTeamMember;
import com.salesforce.industries.pojo.CaseTeamRole;
import com.salesforce.industries.pojo.IndividualResult;
import com.salesforce.industries.pojo.IndustriesCarePlan;
import com.salesforce.industries.pojo.IndustriesRecordType;
import com.salesforce.industries.pojo.User;
import com.salesforce.industries.service.IndustriesRecordTypeService;
import com.salesforce.industries.service.OrgService;
import com.sforce.async.OperationEnum;

@Service
public class BulkCaseService {

    final static Logger logger = Logger.getLogger(BulkCaseService.class);
    @Autowired
    OrgService orgService;
    @Autowired
    IndustriesRecordTypeService irtService;
    @Autowired
    CarePlanGeneratorService carePlanGeneratorService;
    @Autowired
    BulkAccountService bulkAccountService;
    @Autowired
    UserService userService;
    @Autowired
    CaseTeamRoleService caseTeamRoleService;

    public List<IndustriesCarePlan> generateAndPush(List<IndividualResult> indvResults) throws Exception {

        List<BulkResult> caseResults = null;
        List<IndustriesCarePlan> allCarePlans  = null;

        String nameSpace = orgService.getIndustriesNamespace();
        List<IndustriesRecordType> irt = irtService.getIndustriesRecordTypes(Consts.RECORDTYPE_CATEGORY_CAREPLAN);
        String recordType = null;
        for(int i=0;i<irt.size();i++) {
            IndustriesRecordType recType = irt.get(i);
            if(recType.getsObjectType().equalsIgnoreCase(Consts.SOBJECT_CASE)) {
                recordType = recType.getRecordTypeId();
                break;
            }
        }
        if(recordType != null) {
            allCarePlans = new ArrayList<IndustriesCarePlan>();
            for(int i=0;i<indvResults.size();i++) {
                IndividualResult result = indvResults.get(i);
                Integer count = RandUtil.generateRandomInteger(1,10);
                List<CarePlan> carePlans = carePlanGeneratorService.generate(count, recordType, nameSpace,null);
                for(int j=0;j<carePlans.size();j++) {
                    CarePlan carePlan = carePlans.get(j);
                    IndustriesCarePlan industriesCarePlan = new IndustriesCarePlan(carePlan);
                    industriesCarePlan.setIsPrimary(false);
                    if(j==0) {
                        industriesCarePlan.setIsPrimary(true);
                    }
                    industriesCarePlan.setRecordTypeId(recordType);
                    industriesCarePlan.setAccountId(result.getAccountId());
                    industriesCarePlan.setContactId(result.getContactId());
                    industriesCarePlan.setReferenceId(Integer.toString(carePlan.getId()));
                    allCarePlans.add(industriesCarePlan);
                }
            }
            BulkOperation<IndustriesCarePlan> caseBulk = new BulkOperation<IndustriesCarePlan>(orgService.getIndustriesNamespace());
            caseResults = caseBulk.push(allCarePlans,OperationEnum.insert);

            List<String> createdIds = new ArrayList<String>();
            List<AccountCarePlan> accountCarePlans = new ArrayList<AccountCarePlan>();
            for(int i=0;i<caseResults.size();i++) {
                BulkResult result = caseResults.get(i);
                IndustriesCarePlan carePlan = allCarePlans.get(i);
                if(carePlan.getIsPrimary() && result.getCreated()) {
                    AccountCarePlan acctCarePlan = new AccountCarePlan();
                    acctCarePlan.setId(carePlan.getAccountId());
                    acctCarePlan.setPrimaryCarePlanId(result.getId());
                    accountCarePlans.add(acctCarePlan);
                }else {
                    if(!result.getCreated()) {
                    }
                }
                if(result.getCreated()) {
                    carePlan.setId(result.getId());
                    createdIds.add(result.getId());
                }
            }
            //Sync Account Back for PrimaryCarePlanId
            bulkAccountService.updateForCarePlan(accountCarePlans);

            //Add case Team Members
            List<User> users = userService.getUsers();
            List<CaseTeamMember> caseTeamMember = new ArrayList<CaseTeamMember>();
            CaseTeamRole caseTeamPatientRole = caseTeamRoleService.getRoleId("Patient");
            CaseTeamRole caseTeamCareCoordinatorRole = caseTeamRoleService.getRoleId("Care Coordinator");

            if(caseTeamPatientRole == null || caseTeamCareCoordinatorRole == null) {
                throw new Exception("Unable to determine Patient/CareCoordinator Role");
            }

            List<CaseTeamRole> nonPatientRoles = caseTeamRoleService.getNonPatientRoles();
            RandomListUtil<User> rlUser = new RandomListUtil<User>();
            RandomListUtil<CaseTeamRole> rlCaseTeamRole = new RandomListUtil<CaseTeamRole>();

            for(int j=0;j<allCarePlans.size();j++) {
                IndustriesCarePlan cp = allCarePlans.get(j);
                if(cp.getId() != null) {
                    rlUser.reset(users);
                    rlCaseTeamRole.reset(nonPatientRoles);

                    CaseTeamMember patientMember = new CaseTeamMember();
                    patientMember.setCaseId(cp.getId());
                    patientMember.setMemberId(cp.getContactId());
                    patientMember.setTeamRoleId(caseTeamPatientRole.getId());
                    caseTeamMember.add(patientMember);

                    User selectedUser = rlUser.getRandomItem(users);
                    CaseTeamMember careCoordinatorMember = new CaseTeamMember();
                    careCoordinatorMember.setCaseId(cp.getId());
                    careCoordinatorMember.setMemberId(selectedUser.getId());
                    careCoordinatorMember.setTeamRoleId(caseTeamCareCoordinatorRole.getId());
                    caseTeamMember.add(careCoordinatorMember);
                    cp.setCcUser(selectedUser.getId());

                    int memberCount = RandUtil.generateRandomInteger(0, 5);
                    for(int k=0;k<memberCount;k++) {
                        selectedUser = rlUser.getRandomItem(users);
                        CaseTeamRole memberRole = rlCaseTeamRole.getRandomItem(nonPatientRoles);
                        CaseTeamMember nonPatientMember = new CaseTeamMember();
                        nonPatientMember.setCaseId(cp.getId());
                        nonPatientMember.setMemberId(selectedUser.getId());
                        nonPatientMember.setTeamRoleId(memberRole.getId());
                        caseTeamMember.add(nonPatientMember);
                    }
                }
            }
            BulkOperation<CaseTeamMember> caseTeamBulk = new BulkOperation<CaseTeamMember>(orgService.getIndustriesNamespace());
            List<BulkResult> teamMemberResults = caseTeamBulk.push(caseTeamMember,OperationEnum.insert);
            teamMemberResults.size();
        }
        return allCarePlans;

    }
}
