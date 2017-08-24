package org.stg.service.generator.contact;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.stg.domain.contact.IndustriesContact;
import org.stg.exception.DAOException;
import org.stg.generator.BaseGenerator;

public class IndustriesContactGeneratorService extends BaseGenerator<IndustriesContact> {

    @Autowired
    ContactGeneratorService contactGeneratorService;

    protected IndustriesContact getNewRecord() {
        return new IndustriesContact();
    }

    protected IndustriesContact populateRecord(IndustriesContact contact,int recNumber, String recordTypeId,String parentId) throws IOException {
        contactGeneratorService.populateRecord(contact, recNumber, recordTypeId,parentId);

        //Populate IndustriesAccount specific fields here
        //End: Populate IndustriesAccount specific fields here

        return contact;
    }

    public List<IndustriesContact> generate(int recordCount,String recordTypeId,String nameSpace,String parentId) throws IOException, DAOException {
        List<IndustriesContact> contacts = new ArrayList<IndustriesContact>();
        for(int i=0;i<recordCount;i++) {
            contacts.add(populateRecord(getNewRecord(),i,recordTypeId,parentId));
        }
        return contacts;
    }
}
