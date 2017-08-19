package com.salesforce.industries.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesforce.industries.bulk.BulkOperation;
import com.salesforce.industries.core.Consts;
import com.salesforce.industries.pojo.BulkResult;
import com.salesforce.industries.pojo.Contact;
import com.salesforce.industries.pojo.IndividualResult;
import com.salesforce.industries.pojo.IndustriesRecordType;
import com.salesforce.industries.service.AccountService;
import com.salesforce.industries.service.ContactGeneratorService;
import com.salesforce.industries.service.ContactService;
import com.salesforce.industries.service.IndustriesRecordTypeService;
import com.salesforce.industries.service.OrgService;
import com.sforce.async.OperationEnum;

@Service
public class BulkContactService {

    final static Logger logger = Logger.getLogger(BulkContactService.class);
    @Autowired
    OrgService orgService;
    @Autowired
    IndustriesRecordTypeService irtService;
    @Autowired
    AccountService accountService;
    @Autowired
    ContactService contactService;
    @Autowired
    ContactGeneratorService contactGeneratorService;


    public List<IndividualResult> generateAndPush(int count) throws Exception {

        List<IndividualResult> indvResults = new ArrayList<IndividualResult>();

        //Get Org Info
        String nameSpace = orgService.getIndustriesNamespace();
        List<IndustriesRecordType> irt = irtService.getIndustriesRecordTypes(Consts.RECORDTYPE_CATEGORY_INDIVIDUAL);
        String recordType = null;
        for(int i=0;i<irt.size();i++) {
            IndustriesRecordType recType = irt.get(i);
            if(recType.getsObjectType().equalsIgnoreCase(Consts.SOBJECT_CONTACT)) {
                recordType = recType.getRecordTypeId();
                break;
            }
        }
        if(recordType != null) {
            //Generate Mock Data
            List<Contact> contacts = contactGeneratorService.generate(count, recordType, nameSpace,null);
            BulkOperation<Contact> contactBulk = new BulkOperation<Contact>(orgService.getIndustriesNamespace());

            //Push data to the org
            List<BulkResult> results = contactBulk.push(contacts,OperationEnum.insert);

            //Collect result
            List<String> createdIds = new ArrayList<String>();
            for (BulkResult result : results)
            {
                if(result.getCreated()) {
                    createdIds.add(result.getId());
                }
            }


            //Validation
            List<Contact> createdContacts = contactService.getByIds(createdIds);
            for(int i=0;i<createdContacts.size();i++) {
                Contact contact = createdContacts.get(i);
                if(contact.getAccountId() == null || contact.getAccountId().equals("")) {
                    logger.error("Created Contact has no Account Reference");
                    throw new Exception("Created Contact has no Account Reference");
                }
            }

            //Response Fill
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
