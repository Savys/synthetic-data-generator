package org.stg.service.generator.contact;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.stg.core.MockDataSetHolder;
import org.stg.core.RandUtil;
import org.stg.domain.contact.HCContact;
import org.stg.exception.DAOException;
import org.stg.generator.BaseGenerator;

public class HCContactGeneratorService extends BaseGenerator<HCContact> {

    @Autowired
    IndustriesContactGeneratorService industriesContactGeneratorService;
    @Autowired
    MockDataSetHolder mockDataSetHolder;

    protected HCContact getNewRecord() {
        return new HCContact();
    }

    protected HCContact populateRecord(HCContact contact,int recNumber, String recordTypeId,String parentId) throws IOException {

        industriesContactGeneratorService.populateRecord(contact, recNumber, recordTypeId,parentId);

        for(int i=0;i<mockDataSetHolder.firstNameList.size();i++) {
            if(mockDataSetHolder.firstNameList.get(i).getName().equals(contact.getFirstName())) {
                contact.setGender(mockDataSetHolder.firstNameList.get(i).getGender());
                break;
            }
        }

        /* Health Cloud Specic data generation logic here */
        contact.setSourceSystemId(RandUtil.generateRandomInteger(1000,99999)+"-"+RandUtil.generateRandomInteger(1000,99999)+"-"+RandUtil.generateRandomInteger(1000,99999));
        contact.setMedicalRecordNumber(RandUtil.generateRandomInteger(1000,999999)+"-"+RandUtil.generateRandomInteger(1000,9999)+RandUtil.generateRandomInteger(1000,999999));
        contact.setSourceSystem("EMR1");
        /* EndOf:  Health Cloud Specic data generation logic here */
        return contact;
    }

    public List<HCContact> generate(int recordCount,String recordTypeId,String nameSpace,String parentId) throws IOException, DAOException {
        List<HCContact> contacts = new ArrayList<HCContact>();
        for(int i=0;i<recordCount;i++) {
            contacts.add(populateRecord(getNewRecord(),i,recordTypeId,parentId));
        }
        return contacts;
    }
}
