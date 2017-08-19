package com.salesforce.industries.pojo;

import com.salesforce.industries.core.Consts;
import com.salesforce.industries.core.OrgUtils;

public class Contact extends PojoBase{
	String Id = new String();
	String accountId = new String();

	String uniqueIdentifier = new String();
	String firstName  = new String();
	String lastName  = new String ();
	String gender  = new String ();
	int streetNumber = 0;
	String street  = new String ();
	String city  = new String ();
	String postalCode  = new String ();
	String state  = new String ();
	String country = "United States of America";
	String fullName = new String();

	String email;
	String medicalRecordNumber;
	String sourceSystemId;
	String sourceSystem = "EMR1";

	String address;
	String recordTypeId;

	String dateOfBirth;

	String homePhone;
	String phone;
	String mobilePhone;
	String otherPhone;

	public String toCSVString() {
		return firstName + "," + lastName + "," + gender + ","
				+ streetNumber + " " + street + "," + city + "," + postalCode + ","
				+ state + "," + country + "," + email + "," + medicalRecordNumber
				+ "," + sourceSystemId + "," + sourceSystem + ","
				+ dateOfBirth + "," + recordTypeId;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFullName(String firstName,String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = firstName + ' ' + lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMedicalRecordNumber() {
		return medicalRecordNumber;
	}

	public void setMedicalRecordNumber(String medicalRecordNumber) {
		this.medicalRecordNumber = medicalRecordNumber;
	}

	public String getSourceSystemId() {
		return sourceSystemId;
	}

	public void setSourceSystemId(String sourceSystemId) {
		this.sourceSystemId = sourceSystemId;
	}

	public String getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRecordTypeId() {
		return recordTypeId;
	}

	public void setRecordTypeId(String recordTypeId) {
		this.recordTypeId = recordTypeId;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String accountName) {
		this.fullName = accountName;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getOtherPhone() {
		return otherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}

    @Override
    public String getCSVHeader(String nameSpace) {
        return "FirstName,LastName,"+OrgUtils.getFQFieldName(nameSpace,"Gender__c")+",MailingStreet,MailingCity,MailingPostalCode,MailingState,MailingCountry,Email,"+OrgUtils.getFQFieldName(nameSpace,"MedicalRecordNumber__c")+","+OrgUtils.getFQFieldName(nameSpace,"SourceSystemId__c")+","+OrgUtils.getFQFieldName(nameSpace,"SourceSystem__c")+",Birthdate,RecordTypeId,HomePhone,Phone,MobilePhone,OtherPhone";
    }

    @Override
    public String getObjectName(String nameSpace) {
        return Consts.SOBJECT_CONTACT;
    }

    @Override
    public String toCsvString() {
        return firstName + "," + lastName + "," + gender + ","
                + streetNumber + " " + street + "," + city + "," + postalCode + ","
                + state + "," + country + "," + email + "," + medicalRecordNumber
                + "," + sourceSystemId + "," + sourceSystem + ","
                + dateOfBirth + "," + recordTypeId + ","
                +homePhone+"," +phone+"," +mobilePhone+"," +otherPhone;
    }


}
