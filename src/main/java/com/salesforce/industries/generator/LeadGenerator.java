package com.salesforce.industries.generator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.salesforce.industries.persistence.AddressDAO;
import com.salesforce.industries.persistence.FirstNameDAO;
import com.salesforce.industries.persistence.LastNameDAO;
import com.salesforce.industries.persistence.impl.AddressDAOImpl;
import com.salesforce.industries.persistence.impl.FirstNameDAOImpl;
import com.salesforce.industries.persistence.impl.LastNameDAOImpl;
import com.salesforce.industries.persistence.model.Address;
import com.salesforce.industries.persistence.model.FirstName;
import com.salesforce.industries.persistence.model.LastName;
import com.salesforce.industries.persistence.model.Lead;
import com.salesforce.industries.core.RandUtil;
import com.salesforce.industries.exception.DAOException;

public class LeadGenerator {
    List<FirstName> firstNameList = new ArrayList<FirstName>();
    List<LastName> lastNameList = new ArrayList<LastName>();
    List<Address> addressList = new ArrayList<Address>();
    Set<Integer> firstNameRandRecNumbs = new HashSet<Integer>();
    Set<Integer> lastNameRandRecNumbs = new HashSet<Integer>();
    Set<Integer> addressRandRecNumbs = new HashSet<Integer>();
    Set<String> fullNames = new HashSet<String>();

    LeadGenerator() throws IOException, DAOException {
        FirstNameDAO firstNamesDAO = new FirstNameDAOImpl();
        firstNameList = firstNamesDAO.selectAll();

        LastNameDAO lastNamesDAO = new LastNameDAOImpl();
        lastNameList = lastNamesDAO.selectAll();

        AddressDAO addressDAO = new AddressDAOImpl();
        addressList = addressDAO.selectAll();

        // readFile();
    }

    public Lead getRecord(int recNumber) throws IOException {
        Lead lead = new Lead();

        int tryCount = 0;

        Integer firstNameRand = RandUtil.generateRandomInteger(0, firstNameList.size() - 1);
        Integer lastNameRand = RandUtil.generateRandomInteger(0, lastNameList.size() - 1);
        FirstName firstName = firstNameList.get(firstNameRand);
        LastName lastName = lastNameList.get(lastNameRand);
        String fullName = firstName.getName() + " " + lastName.getLastname();

        while (tryCount < 100) {
            firstNameRand = RandUtil.generateRandomInteger(0, firstNameList.size() - 1);
            lastNameRand = RandUtil.generateRandomInteger(0, lastNameList.size() - 1);
            firstName = firstNameList.get(firstNameRand);
            lastName = lastNameList.get(lastNameRand);

            fullName = firstName.getName() + " " + lastName.getLastname();
            if (fullNames.contains(fullName)) {
                tryCount++;
                continue;
            } else {
                fullNames.add(fullName);
                break;
            }
        }

        Address address = addressList.get(RandUtil.generateRandomInteger(0, addressList.size() - 1));
        int houseNumber = RandUtil.generateRandomInteger(address.getHousenumberstart(), address.getHousenumberend());
        int aptNumber = RandUtil.generateRandomInteger(1, 250);

        lead.setFirstName(firstName.getName());
        lead.setGender(firstName.getGender());
        lead.setLastName(lastName.getLastname());
        lead.setCompany(fullName);

        lead.setStreetNumber(houseNumber);
        lead.setStreet(address.getStreet() + " #" + aptNumber);

        lead.setCity(address.getCity());
        lead.setPostalCode(address.getPostalcode());
        lead.setState(address.getState());
        int batch = recNumber % 10;
        String emailDomain = "hc" + batch + ".org";
        String email = lead.getFirstName() + '.' + lead.getLastName() + "@" + emailDomain;
        lead.setEmail(email.replace(" ", "_"));

        lead.setMedicalRecordNumber(RandUtil.generateRandomInteger(1000, 999999) + "-"
                + RandUtil.generateRandomInteger(1000, 9999) + RandUtil.generateRandomInteger(1000, 999999));
        lead.setSourceSystemId(RandUtil.generateRandomInteger(1000, 99999) + "-"
                + RandUtil.generateRandomInteger(1000, 99999) + "-" + RandUtil.generateRandomInteger(1000, 99999));
        lead.setDateOfBirth(RandUtil.generateRandomDate());
        // lead.setRecordTypeId("012xx00000022ss");
        lead.setRecordTypeId("012B0000000KWf3");
        return lead;
    }

    public static void main(String[] args) throws IOException, DAOException {
        // TODO Auto-generated method stub
        LeadGenerator lg = new LeadGenerator();
        int recordCount = 100000;
        PrintWriter writer = new PrintWriter("/Users/sriram.gopalan/data/HC/data/leadList1.csv", "UTF-8");
        writer.println(
                "First Name,Last Name,Gender,Street,City,PostalCode,State,Country,Email,Medical Record Number,Source System Id,Source System,Birth Date,RecordTypeId,Company");
        Lead lead;
        for (int i = 0; i < recordCount; i++) {
            lead = lg.getRecord(i);
            writer.println(lead.toCSVString());
        }
        writer.close();
    }

}
