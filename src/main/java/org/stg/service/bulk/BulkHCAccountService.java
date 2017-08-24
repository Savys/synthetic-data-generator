package org.stg.service.bulk;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stg.bulk.BulkOperation;
import org.stg.core.Consts;
import org.stg.domain.AccountCarePlan;
import org.stg.domain.account.HCAccount;
import org.stg.domain.account.IndustriesAccount;
import org.stg.domain.contact.Contact;
import org.stg.pojo.BulkResult;
import org.stg.pojo.IndividualResult;
import org.stg.service.BaseIndustryService;
import org.stg.service.ContactService;
import org.stg.service.IndustriesAccountService;
import org.stg.service.generator.account.HCAccountGeneratorService;

import com.sforce.async.OperationEnum;

@Service
public class BulkHCAccountService extends BaseIndustryService{

    final static Logger logger = Logger.getLogger(BulkHCAccountService.class);

    @Autowired
    IndustriesAccountService accountService;
    @Autowired
    HCAccountGeneratorService hcAccountGeneratorService;
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
        String recordType = getIndividualRecordTypeId(Consts.SOBJECT_ACCOUNT);

        if(recordType != null) {
            List<HCAccount> accounts = hcAccountGeneratorService.generate(count, recordType, nameSpace,null);
            BulkOperation<HCAccount> acctBulk = new BulkOperation<HCAccount>(nameSpace);
            List<BulkResult> results = acctBulk.push(accounts,OperationEnum.insert);

            List<String> createdIds = new ArrayList<String>();
            for (BulkResult result : results)
            {
                if(result.getCreated()) {
                    createdIds.add(result.getId());
                }
            }

            //Validation
            List<IndustriesAccount> createdAccounts = accountService.getByIds(createdIds);
            for(int i=0;i<createdAccounts.size();i++) {
                IndustriesAccount account = createdAccounts.get(i);
                if(account.getPrimaryContactId() == null || account.getPrimaryContactId().equals("")) {

                }else {
                    logger.error("Created Account has Contact Reference:"+account.getPrimaryContactId());
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
                    indvResults.add(indvResult);
                }
            }
        }
        return indvResults;

    }
}
