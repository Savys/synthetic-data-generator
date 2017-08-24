package org.stg.service.generator.contact;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.stg.core.RandUtil;
import org.stg.domain.contact.Contact;
import org.stg.exception.DAOException;
import org.stg.generator.BaseGenerator;
import org.stg.persistence.model.Address;
import org.stg.persistence.model.FirstName;
import org.stg.persistence.model.LastName;


public class ContactGeneratorService extends BaseGenerator<Contact> {

    protected Contact getNewRecord() {
        return new Contact();
    }


    protected Contact populateRecord(Contact contact,int recNumber, String recordTypeId,String parentId) throws IOException {
        int tryCount = 0;

        Integer firstNameRand = RandUtil.generateRandomInteger(0,mockDataSet.firstNameList.size()-1);
        Integer lastNameRand = RandUtil.generateRandomInteger(0,mockDataSet.lastNameList.size()-1);
        FirstName firstName = mockDataSet.firstNameList.get(firstNameRand);
        LastName lastName =  mockDataSet.lastNameList.get(lastNameRand);
        String fullName =firstName.getName() + " " + lastName.getLastname();

        while(tryCount < 100) {
            firstNameRand = RandUtil.generateRandomInteger(0,mockDataSet.firstNameList.size()-1);
            lastNameRand = RandUtil.generateRandomInteger(0,mockDataSet.lastNameList.size()-1);
            firstName = mockDataSet.firstNameList.get(firstNameRand);
            lastName = mockDataSet.lastNameList.get(lastNameRand);

            fullName =firstName.getName() + " " + lastName.getLastname();
            if(mockDataSet.fullNames.contains(fullName)) {
                tryCount++;
                continue;
            }else {
                mockDataSet.fullNames.add(fullName);
                break;
            }
        }

        Address address = mockDataSet.addressList.get(RandUtil.generateRandomInteger(0,mockDataSet.addressList.size()-1));
        int houseNumber = RandUtil.generateRandomInteger(address.getHousenumberstart(),address.getHousenumberend());
        int aptNumber = RandUtil.generateRandomInteger(1,250);

        contact.setFullName(firstName.getName(),lastName.getLastname());
        //contact.setGender(firstName.getGender());

        contact.setStreetNumber(houseNumber);
        contact.setStreet(address.getStreet() + " #"+aptNumber);
        contact.setCity(address.getCity());
        contact.setPostalCode(address.getPostalcode());
        contact.setState(address.getState());

        address = mockDataSet.addressList.get(RandUtil.generateRandomInteger(0,mockDataSet.addressList.size()-1));
        houseNumber = RandUtil.generateRandomInteger(address.getHousenumberstart(),address.getHousenumberend());
        aptNumber = RandUtil.generateRandomInteger(1,250);
        contact.setOtherStreetNumber(houseNumber);
        contact.setOtherStreet(address.getStreet() + " #"+aptNumber);
        contact.setOtherCity(address.getCity());
        contact.setOtherPostalCode(address.getPostalcode());
        contact.setOtherState(address.getState());


        int batch = recNumber%10;
        String emailDomain = "industries"+batch+".org";
        String email = contact.getFirstName() + '.'+contact.getLastName() + "@"+emailDomain;
        contact.setEmail(email.replace(" ", "_"));

        contact.setDateOfBirth(RandUtil.generateRandomDate());
        contact.setRecordTypeId(recordTypeId);

        contact.setPhone(RandUtil.generateRandomPhone());
        contact.setHomePhone(RandUtil.generateRandomPhone());
        contact.setOtherPhone(RandUtil.generateRandomPhone());
        contact.setMobilePhone(RandUtil.generateRandomPhone());

        return contact;
    }

    public List<Contact> generate(int recordCount,String recordTypeId,String nameSpace,String parentId) throws IOException, DAOException {
        List<Contact> contacts = new ArrayList<Contact>();
        for(int i=0;i<recordCount;i++) {
            contacts.add(populateRecord(getNewRecord(),i,recordTypeId,parentId));
        }
        return contacts;
    }
}
