package com.salesforce.industries.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesforce.industries.bulk.BulkOperation;
import com.salesforce.industries.core.Consts;
import com.salesforce.industries.pojo.Account;
import com.salesforce.industries.pojo.AccountCarePlan;
import com.salesforce.industries.pojo.BulkResult;
import com.salesforce.industries.pojo.Contact;
import com.salesforce.industries.pojo.IndividualResult;
import com.salesforce.industries.pojo.IndustriesRecordType;
import com.salesforce.industries.service.AccountService;
import com.salesforce.industries.service.IndustriesRecordTypeService;
import com.salesforce.industries.service.OrgService;
import com.sforce.async.OperationEnum;

@Service
public class BulkAccountService {

    final static Logger logger = Logger.getLogger(BulkAccountService.class);
    @Autowired
    OrgService orgService;
    @Autowired
    IndustriesRecordTypeService irtService;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountGeneratorService accountGeneratorService;
    @Autowired
    ContactService contactService;

    public void updateForCarePlan(List<AccountCarePlan> primary) throws Exception {
        BulkOperation<AccountCarePlan> acctBulk = new BulkOperation<AccountCarePlan>(orgService.getIndustriesNamespace());
        List<BulkResult> results = acctBulk.push(primary,OperationEnum.update);
        logger.debug(results);
    }

    public List<IndividualResult> generateAndPush(int count) throws Exception {

        List<IndividualResult> indvResults = new ArrayList<IndividualResult>();

        String nameSpace = orgService.getIndustriesNamespace();
        List<IndustriesRecordType> irt = irtService.getIndustriesRecordTypes(Consts.RECORDTYPE_CATEGORY_INDIVIDUAL);
        String recordType = null;
        for(int i=0;i<irt.size();i++) {
            IndustriesRecordType recType = irt.get(i);
            if(recType.getsObjectType().equalsIgnoreCase(Consts.SOBJECT_ACCOUNT)) {
                recordType = recType.getRecordTypeId();
                break;
            }
        }
        if(recordType != null) {
            List<Account> accounts = accountGeneratorService.generate(count, recordType, nameSpace,null);
            BulkOperation<Account> acctBulk = new BulkOperation<Account>(orgService.getIndustriesNamespace());
            List<BulkResult> results = acctBulk.push(accounts,OperationEnum.insert);

            List<String> createdIds = new ArrayList<String>();
            for (BulkResult result : results)
            {
                if(result.getCreated()) {
                    createdIds.add(result.getId());
                }
            }

            //Validation
            List<Account> createdAccounts = accountService.getByIds(createdIds);
            for(int i=0;i<createdAccounts.size();i++) {
                Account account = createdAccounts.get(i);
                if(account.getPrimaryContactId() == null || account.getPrimaryContactId().equals("")) {

                }else {
                    logger.error("Created Account has Account Reference:"+account.getPrimaryContactId());
                    //throw new Exception("Created Account has Contact Reference");
                }
            }

            //Response Fill
            List<Contact> createdContacts = contactService.getByAccountIds(createdIds);
            for(int i=0;i<createdContacts.size();i++) {
                Contact contact = createdContacts.get(i);
                if(contact != null) {
                    IndividualResult indvResult = new IndividualResult();
                    indvResult.setAccountId(contact.getAccountId());
                    indvResult.setContactId(contact.getId());
                    logger.debug(indvResult);
                    indvResults.add(indvResult);
                }
            }
        }
        return indvResults;

    }
}
