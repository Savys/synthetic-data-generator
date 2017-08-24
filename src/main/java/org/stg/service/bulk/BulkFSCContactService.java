package org.stg.service.bulk;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stg.bulk.BulkOperation;
import org.stg.core.Consts;
import org.stg.domain.contact.Contact;
import org.stg.domain.contact.FSCContact;
import org.stg.pojo.BulkResult;
import org.stg.pojo.IndividualResult;
import org.stg.service.BaseIndustryService;
import org.stg.service.ContactService;
import org.stg.service.IndustriesAccountService;
import org.stg.service.IndustriesRecordTypeService;
import org.stg.service.OrgService;
import org.stg.service.generator.contact.FSCContactGeneratorService;

import com.sforce.async.OperationEnum;

@Service
public class BulkFSCContactService extends BaseIndustryService {

    final static Logger logger = Logger.getLogger(BulkFSCContactService.class);
    @Autowired
    OrgService orgService;
    @Autowired
    IndustriesRecordTypeService irtService;
    @Autowired
    IndustriesAccountService accountService;
    @Autowired
    ContactService contactService;
    @Autowired
    FSCContactGeneratorService contactGeneratorService;


    public List<IndividualResult> generateAndPush(int count) throws Exception {

        List<IndividualResult> indvResults = new ArrayList<IndividualResult>();

        //Get Org Info
        String nameSpace = orgService.getIndustriesNamespace();
        String recordType = getIndividualRecordTypeId(Consts.SOBJECT_CONTACT);

        if(recordType != null) {
            //Generate Mock Data
            List<FSCContact> contacts = contactGeneratorService.generate(count, recordType, nameSpace,null);
            BulkOperation<FSCContact> contactBulk = new BulkOperation<FSCContact>(orgService.getIndustriesNamespace());

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
