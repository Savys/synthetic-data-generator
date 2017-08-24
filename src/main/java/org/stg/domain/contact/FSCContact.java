package org.stg.domain.contact;

import java.util.ArrayList;
import java.util.List;

import org.stg.core.Consts;
import org.stg.core.OrgUtils;

public class FSCContact extends IndustriesContact{

    String affiliations;
    Double annualIncome = 0.0d;
    String citizenship;
    String communicationPreferences;
    String contactPreference;
    String countryOfResidence;
    String countryOfBirth;
    Boolean createdFromLead = false;
    String currentEmployer;
    String customerTimezone;
    Boolean emailVerified = false;
    String employedSince;
    Boolean faxVerified = false;
    String homeOwnership;
    Boolean homePhoneVerified = false;
    String lastFourDigitSSN;
    String level;
    String maritalStatus;
    Boolean marketingOptOut = false;
    Boolean mobileVerified = false;
    String mostUsedChannel;
    String motherMaidenName;
    String nextLifeEvent;
    Integer numberOfChildren = 0;
    Integer numberOfDependents = 0;
    String occupation;
    String preferredName;
    Boolean primaryAddressIsBilling = false;
    Boolean primaryAddressIsMailing = false;
    Boolean primaryAddressIsOther = false;
    Boolean primaryAddressIsShipping = false;
    String primaryCitizenship;
    String primaryLanguage;
    String secondaryCitizenship;
    String secondaryLanguage;
    String sourceSystemId;
    String taxBracket;
    String taxId;
    String weddingAnniversary;

    public String getAffiliations() {
        return affiliations;
    }

    public Double getAnnualIncome() {
        return annualIncome;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public String getCommunicationPreferences() {
        return communicationPreferences;
    }

    public String getContactPreference() {
        return contactPreference;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    public Boolean getCreatedFromLead() {
        return createdFromLead;
    }

    public String getCurrentEmployer() {
        return currentEmployer;
    }

    public String getCustomerTimezone() {
        return customerTimezone;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public String getEmployedSince() {
        return employedSince;
    }

    public Boolean getFaxVerified() {
        return faxVerified;
    }

    public String getGender() {
        return gender;
    }

    public String getHomeOwnership() {
        return homeOwnership;
    }

    public Boolean getHomePhoneVerified() {
        return homePhoneVerified;
    }

    public String getLastFourDigitSSN() {
        return lastFourDigitSSN;
    }

    public String getLevel() {
        return level;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public Boolean getMarketingOptOut() {
        return marketingOptOut;
    }

    public Boolean getMobileVerified() {
        return mobileVerified;
    }

    public String getMostUsedChannel() {
        return mostUsedChannel;
    }

    public String getMotherMaidenName() {
        return motherMaidenName;
    }

    public String getNextLifeEvent() {
        return nextLifeEvent;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public Integer getNumberOfDependents() {
        return numberOfDependents;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public Boolean getPrimaryAddressIsBilling() {
        return primaryAddressIsBilling;
    }

    public Boolean getPrimaryAddressIsMailing() {
        return primaryAddressIsMailing;
    }

    public Boolean getPrimaryAddressIsOther() {
        return primaryAddressIsOther;
    }

    public Boolean getPrimaryAddressIsShipping() {
        return primaryAddressIsShipping;
    }

    public String getPrimaryCitizenship() {
        return primaryCitizenship;
    }

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public String getSecondaryCitizenship() {
        return secondaryCitizenship;
    }

    public String getSecondaryLanguage() {
        return secondaryLanguage;
    }

    public String getSourceSystemId() {
        return sourceSystemId;
    }

    public String getTaxBracket() {
        return taxBracket;
    }

    public String getTaxId() {
        return taxId;
    }

    public String getWeddingAnniversary() {
        return weddingAnniversary;
    }

    public void setAffiliations(String affiliations) {
        this.affiliations = affiliations;
    }

    public void setAnnualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public void setCommunicationPreferences(String communicationPreferences) {
        this.communicationPreferences = communicationPreferences;
    }

    public void setContactPreference(String contactPreference) {
        this.contactPreference = contactPreference;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public void setCreatedFromLead(Boolean createdFromLead) {
        this.createdFromLead = createdFromLead;
    }

    public void setCurrentEmployer(String currentEmployer) {
        this.currentEmployer = currentEmployer;
    }

    public void setCustomerTimezone(String customerTimezone) {
        this.customerTimezone = customerTimezone;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public void setEmployedSince(String employedSince) {
        this.employedSince = employedSince;
    }

    public void setFaxVerified(Boolean faxVerified) {
        this.faxVerified = faxVerified;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHomeOwnership(String homeOwnership) {
        this.homeOwnership = homeOwnership;
    }

    public void setHomePhoneVerified(Boolean homePhoneVerified) {
        this.homePhoneVerified = homePhoneVerified;
    }

    public void setLastFourDigitSSN(String lastFourDigitSSN) {
        this.lastFourDigitSSN = lastFourDigitSSN;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setMarketingOptOut(Boolean marketingOptOut) {
        this.marketingOptOut = marketingOptOut;
    }

    public void setMobileVerified(Boolean mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    public void setMostUsedChannel(String mostUsedChannel) {
        this.mostUsedChannel = mostUsedChannel;
    }

    public void setMotherMaidenName(String motherMaidenName) {
        this.motherMaidenName = motherMaidenName;
    }

    public void setNextLifeEvent(String nextLifeEvent) {
        this.nextLifeEvent = nextLifeEvent;
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public void setNumberOfDependents(Integer numberOfDependents) {
        this.numberOfDependents = numberOfDependents;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public void setPrimaryAddressIsBilling(Boolean primaryAddressIsBilling) {
        this.primaryAddressIsBilling = primaryAddressIsBilling;
    }

    public void setPrimaryAddressIsMailing(Boolean primaryAddressIsMailing) {
        this.primaryAddressIsMailing = primaryAddressIsMailing;
    }

    public void setPrimaryAddressIsOther(Boolean primaryAddressIsOther) {
        this.primaryAddressIsOther = primaryAddressIsOther;
    }

    public void setPrimaryAddressIsShipping(Boolean primaryAddressIsShipping) {
        this.primaryAddressIsShipping = primaryAddressIsShipping;
    }

    public void setPrimaryCitizenship(String primaryCitizenship) {
        this.primaryCitizenship = primaryCitizenship;
    }

    public void setPrimaryLanguage(String primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    public void setSecondaryCitizenship(String secondaryCitizenship) {
        this.secondaryCitizenship = secondaryCitizenship;
    }

    public void setSecondaryLanguage(String secondaryLanguage) {
        this.secondaryLanguage = secondaryLanguage;
    }

    public void setSourceSystemId(String sourceSystemId) {
        this.sourceSystemId = sourceSystemId;
    }

    public void setTaxBracket(String taxBracket) {
        this.taxBracket = taxBracket;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public void setWeddingAnniversary(String weddingAnniversary) {
        this.weddingAnniversary = weddingAnniversary;
    }

    @Override
    public String getCSVHeader(String nameSpace) {
        List<String> fieldNames = new ArrayList<String>();

        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"AFFILIATIONS__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"ANNUALINCOME__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"CITIZENSHIP__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"COMMUNICATIONPREFERENCES__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"CONTACTPREFERENCE__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"COUNTRYOFBIRTH__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"COUNTRYOFRESIDENCE__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"CREATEDFROMLEAD__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"CURRENTEMPLOYER__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"CUSTOMERTIMEZONE__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"EMAILVERIFIED__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"EMPLOYEDSINCE__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"FAXVERIFIED__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"HOMEOWNERSHIP__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"HOMEPHONEVERIFIED__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"LastFourDigitSSN__c"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"LEVEL__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"MARITALSTATUS__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"MARKETINGOPTOUT__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"MOBILEVERIFIED__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"MOSTUSEDCHANNEL__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"MOTHERMAIDENNAME__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"NEXTLIFEEVENT__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"NUMBEROFCHILDREN__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"NUMBEROFDEPENDENTS__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"OCCUPATION__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"PREFERREDNAME__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"PRIMARYADDRESSISBILLING__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"PRIMARYADDRESSISMAILING__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"PRIMARYADDRESSISOTHER__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"PRIMARYADDRESSISSHIPPING__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"PRIMARYCITIZENSHIP__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"PRIMARYLANGUAGE__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"SECONDARYCITIZENSHIP__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"SECONDARYLANGUAGE__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"SOURCESYSTEMID__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"TAXBRACKET__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"TAXID__C"));
        fieldNames.add(OrgUtils.getFQFieldName(nameSpace,"WeddingAnniversary__c"));

        String fieldList = fieldNames.toString();
        String csv = fieldList.substring(1, fieldList.length() - 1).replace(", ", ",");
        String parent = super.getCSVHeader(nameSpace);
        return parent+","+csv;
    }

    @Override
    public String getObjectName(String nameSpace) {
        return Consts.SOBJECT_CONTACT;
    }

    @Override
    public String toCSVString() {
        String parent = super.toCSVString();
        List<String> fieldValues     = new ArrayList<String>();

        fieldValues.add(affiliations);
        fieldValues.add(annualIncome.toString());
        fieldValues.add("\""+citizenship+"\"");
        fieldValues.add("\""+communicationPreferences+"\"");
        fieldValues.add(contactPreference);
        fieldValues.add("\""+countryOfBirth+"\"");
        fieldValues.add("\""+countryOfResidence+"\"");
        fieldValues.add(createdFromLead.toString());
        fieldValues.add("\""+currentEmployer+"\"");
        fieldValues.add("\""+customerTimezone+"\"");
        fieldValues.add(emailVerified.toString());
        fieldValues.add(employedSince);
        fieldValues.add(faxVerified.toString());
        fieldValues.add(homeOwnership);
        fieldValues.add(homePhoneVerified.toString());
        fieldValues.add(lastFourDigitSSN);
        fieldValues.add("\""+level+"\"");
        fieldValues.add("\""+maritalStatus+"\"");
        fieldValues.add(marketingOptOut.toString());
        fieldValues.add(mobileVerified.toString());
        fieldValues.add("\""+mostUsedChannel+"\"");
        fieldValues.add("\""+motherMaidenName+"\"");
        fieldValues.add("\""+nextLifeEvent+"\"");
        fieldValues.add(numberOfChildren.toString());
        fieldValues.add(numberOfDependents.toString());
        fieldValues.add("\""+occupation+"\"");
        fieldValues.add("\""+preferredName+"\"");
        fieldValues.add(primaryAddressIsBilling.toString());
        fieldValues.add(primaryAddressIsMailing.toString());
        fieldValues.add(primaryAddressIsOther.toString());
        fieldValues.add(primaryAddressIsShipping.toString());
        fieldValues.add("\""+primaryCitizenship+"\"");
        fieldValues.add("\""+primaryLanguage+"\"");
        fieldValues.add("\""+secondaryCitizenship+"\"");
        fieldValues.add("\""+secondaryLanguage+"\"");
        fieldValues.add(sourceSystemId);
        fieldValues.add(taxBracket);
        fieldValues.add(taxId);
        fieldValues.add(weddingAnniversary);

        String fieldValueList = fieldValues.toString();
        String csv = fieldValueList.substring(1, fieldValueList.length() - 1).replace(", ", ",");
        return parent+","+csv;
    }


}
