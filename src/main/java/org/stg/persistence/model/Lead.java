package org.stg.persistence.model;

public class Lead extends BaseModel{
	String firstName  = new String();
	String lastName  = new String ();
	String gender  = new String ();
	int streetNumber = 0;
	String street  = new String ();
	String city  = new String ();
	String postalCode  = new String ();
	String state  = new String ();
	String country = "United States of America";
	String company = new String();

	String email;
	String medicalRecordNumber;
	String sourceSystemId;
	String sourceSystem = "EMR1";

	String address;
	String recordTypeId;

	String dateOfBirth;

	@Override
	public String toString() {
		return "Lead [firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", streetNumber="
				+ streetNumber + ", street=" + street + ", city=" + city + ", postalCode=" + postalCode + ", state="
				+ state + ", country=" + country + ", email=" + email + ", medicalRecordNumber=" + medicalRecordNumber
				+ ", sourceSystemId=" + sourceSystemId + ", sourceSystem=" + sourceSystem + ", dateOfBirth="
				+ dateOfBirth + "]";
	}

	public String toCSVString() {
		return firstName + "," + lastName + "," + gender + ","
				+ streetNumber + " " + street + "," + city + "," + postalCode + ","
				+ state + "," + country + "," + email + "," + medicalRecordNumber
				+ "," + sourceSystemId + "," + sourceSystem + ","
				+ dateOfBirth + "," + recordTypeId+"," + company;
	}

	public String getFirstName() {
		return firstName;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}


}
