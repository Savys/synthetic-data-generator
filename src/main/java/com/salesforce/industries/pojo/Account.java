package com.salesforce.industries.pojo;

import com.salesforce.industries.core.Consts;
import com.salesforce.industries.core.OrgUtils;
import com.salesforce.industries.core.RandUtil;

public class Account extends PojoBase{
	String Id = new String();
	String primaryContactId = new String();

	int streetNumber = 0;
	String street  = new String ();
	String city  = new String ();
	String postalCode  = new String ();
	String state  = new String ();
	String country = "United States of America";
	String accountName = new String();
	String medicalRecordNumber;
	String sourceSystemId;
	String sourceSystem = "EMR1";

	String address;
	String recordTypeId;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}


	public void setNames(String firstName,String lastName) {
		this.accountName = firstName + ' ' + lastName;
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

	public String getMedicalRecordNumber() {
		String local = RandUtil.generateRandomInteger(1000,999999)+"-"+RandUtil.generateRandomInteger(1000,9999)+RandUtil.generateRandomInteger(1000,999999);
		this.medicalRecordNumber = local;
		return medicalRecordNumber;
	}

	public void setMedicalRecordNumber(String medicalRecordNumber) {
		this.medicalRecordNumber = medicalRecordNumber;
	}

	public String getSourceSystemId() {
		String local =  RandUtil.generateRandomInteger(1000,99999)+"-"+RandUtil.generateRandomInteger(1000,99999)+"-"+RandUtil.generateRandomInteger(1000,99999);
		this.sourceSystemId = local;
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


	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPrimaryContactId() {
		return primaryContactId;
	}

	public void setPrimaryContactId(String primaryContactId) {
		this.primaryContactId = primaryContactId;
	}

    @Override
    public String getCSVHeader(String nameSpace) {
        return "BillingStreet,BillingCity,BillingPostalCode,BillingState,BillingCountry,"+OrgUtils.getFQFieldName(nameSpace,"MedicalRecordNumber__c")+","+OrgUtils.getFQFieldName(nameSpace,"SourceSystemId__c")+","+OrgUtils.getFQFieldName(nameSpace,"SourceSystem__c")+",RecordTypeId,Name";
    }

    @Override
    public String getObjectName(String nameSpace) {
        return Consts.SOBJECT_ACCOUNT;
    }

    public String toCsvString() {
        return
                + streetNumber + " " + street + "," + city + "," + postalCode + ","
                + state + "," + country + "," + getMedicalRecordNumber()
                + "," + getSourceSystemId() + "," + sourceSystem + ","
                +  recordTypeId+"," + accountName;
    }



}
