package org.stg.domain.account;

import org.stg.core.Consts;
import org.stg.pojo.PojoBase;

public class Account  extends PojoBase implements IAccount{
    String Id = new String();
    int streetNumber = 0;
    String street  = new String ();
    String city  = new String ();
    String postalCode  = new String ();
    String state  = new String ();
    String country = "United States of America";
    String accountName = new String();
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

    @Override
    public String getCSVHeader(String nameSpace) {
        return "BillingStreet,BillingCity,BillingPostalCode,BillingState,BillingCountry,RecordTypeId,Name";
    }

    @Override
    public String getObjectName(String nameSpace) {
        return Consts.SOBJECT_ACCOUNT;
    }

    @Override
    public String toCSVString() {
        return  streetNumber + " " + street + "," + city + "," + postalCode + ","  + state + "," + country + "," + recordTypeId+"," + accountName;
    }

}
