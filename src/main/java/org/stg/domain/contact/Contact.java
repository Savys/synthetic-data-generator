package org.stg.domain.contact;

import java.util.Calendar;

import org.stg.core.Consts;
import org.stg.core.RandUtil;
import org.stg.pojo.PojoBase;

public class Contact extends PojoBase  implements IContact{
	String Id = new String();
	String accountId = new String();

	String uniqueIdentifier = new String();
	String firstName  = new String();
	String lastName  = new String ();
	int streetNumber = 0;
	String street  = new String ();
	String city  = new String ();
	String postalCode  = new String ();
	String state  = new String ();
	String country = "United States of America";
	String fullName = new String();

    int otherStreetNumber = 0;
    String otherStreet  = new String ();
    String otherCity  = new String ();
    String otherPostalCode  = new String ();
    String otherState  = new String ();
    String otherCountry = "United States of America";

	String email;

	String address;
	String recordTypeId;

	Calendar dateOfBirth;

	String homePhone;
	String phone;
	String mobilePhone;
	String otherPhone;

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
	    return RandUtil.getFormattedDate(dateOfBirth);
	}

	public void setDateOfBirth(Calendar dateOfBirth) {
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

    public int getOtherStreetNumber() {
        return otherStreetNumber;
    }

    public String getOtherStreet() {
        return otherStreet;
    }

    public String getOtherCity() {
        return otherCity;
    }

    public String getOtherPostalCode() {
        return otherPostalCode;
    }

    public String getOtherState() {
        return otherState;
    }

    public String getOtherCountry() {
        return otherCountry;
    }

    public void setOtherStreetNumber(int otherStreetNumber) {
        this.otherStreetNumber = otherStreetNumber;
    }

    public void setOtherStreet(String otherStreet) {
        this.otherStreet = otherStreet;
    }

    public void setOtherCity(String otherCity) {
        this.otherCity = otherCity;
    }

    public void setOtherPostalCode(String otherPostalCode) {
        this.otherPostalCode = otherPostalCode;
    }

    public void setOtherState(String otherState) {
        this.otherState = otherState;
    }

    public void setOtherCountry(String otherCountry) {
        this.otherCountry = otherCountry;
    }

    @Override
    public String getCSVHeader(String nameSpace) {
        return "FirstName,LastName,MailingStreet,MailingCity,MailingPostalCode,MailingState,MailingCountry,Email,Birthdate,RecordTypeId,HomePhone,Phone,MobilePhone,OtherPhone,OtherStreet,OtherCity,OtherPostalCode,OtherState,OtherCountry";
    }

    @Override
    public String getObjectName(String nameSpace) {
        return Consts.SOBJECT_CONTACT;
    }

    @Override
    public String toCSVString() {
        return firstName + "," + lastName +
                ",\""  + streetNumber + " " + street + "\",\"" + city + "\"," + postalCode + ",\""  + state + "\",\"" + country + "\""
                + "," + email + ","
                + getDateOfBirth() + "," + recordTypeId + ","
                +homePhone+"," +phone+"," +mobilePhone+"," +otherPhone
                + ",\"" + otherStreetNumber + " " + otherStreet + "\",\"" + otherCity + "\"," + otherPostalCode + ",\""  + otherState + "\",\"" + otherCountry + "\"";
    }


}
