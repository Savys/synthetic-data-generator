package com.salesforce.industries.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.salesforce.industries.core.RandUtil;
import com.salesforce.industries.exception.DAOException;
import com.salesforce.industries.generator.BaseGenerator;
import com.salesforce.industries.persistence.model.Address;
import com.salesforce.industries.persistence.model.FirstName;
import com.salesforce.industries.persistence.model.LastName;
import com.salesforce.industries.pojo.Contact;


public class ContactGeneratorService extends BaseGenerator<Contact> {


    public ContactGeneratorService() throws DAOException {
        super();
    }

    private Contact getRecord(int recNumber, String recordTypeId) throws IOException {

        Contact contact = new Contact();

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
        contact.setGender(firstName.getGender());

        contact.setStreetNumber(houseNumber);
        contact.setStreet(address.getStreet() + " #"+aptNumber);

        contact.setCity(address.getCity());
        contact.setPostalCode(address.getPostalcode());
        contact.setState(address.getState());
        int batch = recNumber%10;
        String emailDomain = "hc"+batch+".org";
        String email = contact.getFirstName() + '.'+contact.getLastName() + "@"+emailDomain;
        contact.setEmail(email.replace(" ", "_"));

        contact.setMedicalRecordNumber(RandUtil.generateRandomInteger(1000,999999)+"-"+RandUtil.generateRandomInteger(1000,9999)+RandUtil.generateRandomInteger(1000,999999));
        contact.setSourceSystemId(RandUtil.generateRandomInteger(1000,99999)+"-"+RandUtil.generateRandomInteger(1000,99999)+"-"+RandUtil.generateRandomInteger(1000,99999));
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
            contacts.add(getRecord(i,recordTypeId));
        }
        return contacts;
    }
}
