package org.stg.service.generator.contact;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.stg.core.Consts;
import org.stg.core.MockDataSetHolder;
import org.stg.core.RandUtil;
import org.stg.domain.contact.FSCContact;
import org.stg.exception.DAOException;
import org.stg.generator.BaseGenerator;
import org.stg.persistence.model.Occupation;
import org.stg.service.CustomObjectMetadataService;
import org.stg.service.LayoutMetadataService;



public class FSCContactGeneratorService extends BaseGenerator<FSCContact> {

    final static Logger logger = Logger.getLogger(FSCContactGeneratorService.class);

    @Autowired
    IndustriesContactGeneratorService industriesContactGeneratorService;
    @Autowired
    CustomObjectMetadataService customObjectMetadataService;
    @Autowired
    MockDataSetHolder mockDataSetHolder;
    @Autowired
    LayoutMetadataService layoutMetadataService;

    protected FSCContact getNewRecord() {
        return new FSCContact();
    }

    protected FSCContact populateRecord(FSCContact contact,int recNumber, String recordTypeId,String parentId) throws Exception {
        industriesContactGeneratorService.populateRecord(contact, recNumber, recordTypeId,parentId);

        /* Wealth Cloud Specic data generation logic here */
        contact.setAffiliations("Lake Switzerland College");
        contact.setCitizenship(customObjectMetadataService.getRandomPickListValue(Consts.SOBJECT_CONTACT, "PrimaryCitizenship__c"));

        contact.setCommunicationPreferences(customObjectMetadataService.getRandomPickListValue(Consts.SOBJECT_CONTACT, "COMMUNICATIONPREFERENCES__C"));
        contact.setContactPreference(customObjectMetadataService.getRandomPickListValue(Consts.SOBJECT_CONTACT, "CONTACTPREFERENCE__C"));
        contact.setCountryOfResidence(contact.getCountry());
        contact.setHomeOwnership(customObjectMetadataService.getRandomPickListValue(Consts.SOBJECT_CONTACT, "HOMEOWNERSHIP__C"));
        contact.setLevel(customObjectMetadataService.getRandomPickListValue(Consts.SOBJECT_CONTACT, "LEVEL__C"));
        contact.setMaritalStatus(customObjectMetadataService.getRandomPickListValue(Consts.SOBJECT_CONTACT, "MARITALSTATUS__C"));
        contact.setMostUsedChannel(customObjectMetadataService.getRandomPickListValue(Consts.SOBJECT_CONTACT, "MOSTUSEDCHANNEL__C"));
        contact.setNextLifeEvent(customObjectMetadataService.getRandomPickListValue(Consts.SOBJECT_CONTACT, "NEXTLIFEEVENT__C"));
        contact.setPrimaryCitizenship(layoutMetadataService.getRandomPickListValue(Consts.SOBJECT_CONTACT, recordTypeId,"PrimaryCitizenship__c"));
        contact.setPrimaryLanguage("English");
        contact.setSecondaryCitizenship(layoutMetadataService.getRandomPickListValue(Consts.SOBJECT_CONTACT, recordTypeId,"SECONDARYCITIZENSHIP__C"));
        contact.setSecondaryLanguage(customObjectMetadataService.getRandomPickListValue(Consts.SOBJECT_CONTACT, "SECONDARYLANGUAGE__C"));
        contact.setTaxBracket(customObjectMetadataService.getRandomPickListValue(Consts.SOBJECT_CONTACT, "TAXBRACKET__C"));

        contact.setCountryOfBirth(customObjectMetadataService.getRandomPickListValue(Consts.SOBJECT_CONTACT, "CountryOfBirth__c"));
        contact.setCurrentEmployer(mockDataSetHolder.fortune200List.get(RandUtil.generateRandomInteger(0,mockDataSetHolder.fortune200List.size()-1)).getName());
        contact.setCreatedFromLead(false);
        contact.setEmailVerified(RandUtil.generateRandomBoolean());
        contact.setFaxVerified(RandUtil.generateRandomBoolean());
        contact.setHomePhoneVerified(RandUtil.generateRandomBoolean());
        contact.setMarketingOptOut(RandUtil.generateRandomBoolean());
        contact.setMobileVerified(RandUtil.generateRandomBoolean());

        contact.setPrimaryAddressIsBilling(RandUtil.generateRandomBoolean());
        contact.setPrimaryAddressIsMailing(RandUtil.generateRandomBoolean());
        contact.setPrimaryAddressIsOther(RandUtil.generateRandomBoolean());
        contact.setPrimaryAddressIsShipping(RandUtil.generateRandomBoolean());

        contact.setPrimaryAddressIsBilling(false);
        contact.setPrimaryAddressIsMailing(true);
        contact.setPrimaryAddressIsOther(false);
        contact.setPrimaryAddressIsShipping(false);
        contact.setNumberOfChildren(RandUtil.generateRandomInteger(0, 5));
        contact.setNumberOfDependents(contact.getNumberOfChildren() + RandUtil.generateRandomInteger(1, 3));
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        Calendar dateofBirth = RandUtil.generateRandomDOB(currentYear-50,currentYear-30);
        contact.setDateOfBirth(dateofBirth);

        int yearsOfEmp = RandUtil.generateRandomInteger(1, 15);
        Calendar employeedSince = Calendar.getInstance();
        employeedSince.add(Calendar.YEAR, -yearsOfEmp);
        contact.setEmployedSince(RandUtil.getFormattedDate(employeedSince));
        contact.setSourceSystemId(RandUtil.generateRandomInteger(1000,99999)+"-"+RandUtil.generateRandomInteger(1000,99999)+"-"+RandUtil.generateRandomInteger(1000,99999));
        String last4Tax = String.valueOf(RandUtil.generateRandomInteger(1000,9999));
        contact.setTaxId(RandUtil.generateRandomInteger(100,999)+"-"+RandUtil.generateRandomInteger(10,99)+"-"+last4Tax);
        contact.setLastFourDigitSSN(last4Tax);

        int occupationIndex = RandUtil.generateRandomInteger(0, mockDataSet.occupationWithSalary.size()-1);
        Occupation occupation = mockDataSet.occupationWithSalary.get(occupationIndex);
        contact.setOccupation(occupation.getName());
        contact.setAnnualIncome((double) RandUtil.generateRandomDouble(occupation.getAnnualizedSalary()-20000,occupation.getAnnualizedSalary()+60000));
        for(int i=0;i<mockDataSetHolder.firstNameList.size();i++) {
            if(mockDataSetHolder.firstNameList.get(i).getName().equals(contact.getFirstName())) {
                contact.setGender(mockDataSetHolder.firstNameList.get(i).getGender());
                break;
            }
        }

        contact.setCustomerTimezone("");
        contact.setWeddingAnniversary("");
        contact.setPreferredName("");
        /* EndOf:  Health Cloud Specic data generation logic here */
        return contact;
    }

    public List<FSCContact> generate(int recordCount,String recordTypeId,String nameSpace,String parentId) throws IOException, DAOException,Exception {
        List<FSCContact> contacts = new ArrayList<FSCContact>();
        for(int i=0;i<recordCount;i++) {
            contacts.add(populateRecord(getNewRecord(),i,recordTypeId,parentId));
        }
        return contacts;
    }
}
