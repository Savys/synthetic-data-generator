package org.stg.persistence.model;

public class Practitioner extends BaseModel{
	private int id;
	private String sourcesystemid;
	private String sourcesystem;
	private String name;
	private String firstname;
	private String lastname;
	private String gender;
	private Integer age;
	private String street;
	private String city;
	private String state;
	private String postalcode;
	private String country;
	private String specality;
	private String subspecality;
	private String clinicalintrest;
	private String experience;
	private String photourl;
	private String sourceurl;
	private String createts;
	private String birthDate;
	private String effectiveStart;
	private String effectiveEnd;

	private String specalityCode;

	private String SubSpecality1;
	private String SubSpecality2;
	private String SubSpecality3;

	private String SubSpecalityCode1;
	private String SubSpecalityCode2;
	private String SubSpecalityCode3;

	public String getSpecalityCode() {
		return specalityCode;
	}

	public void setSpecalitycode(String specalityCode) {
		this.specalityCode = specalityCode;
	}

	public String getSubSpecality1() {
		return SubSpecality1;
	}

	public void setSubSpecality1(String subSpecality1) {
		SubSpecality1 = subSpecality1;
	}

	public String getSubSpecality2() {
		return SubSpecality2;
	}

	public void setSubSpecality2(String subSpecality2) {
		SubSpecality2 = subSpecality2;
	}

	public String getSubSpecality3() {
		return SubSpecality3;
	}

	public void setSubSpecality3(String subSpecality3) {
		SubSpecality3 = subSpecality3;
	}

	public String getSubSpecalityCode1() {
		return SubSpecalityCode1;
	}

	public void setSubSpecalityCode1(String subSpecalityCode1) {
		SubSpecalityCode1 = subSpecalityCode1;
	}

	public String getSubSpecalityCode2() {
		return SubSpecalityCode2;
	}

	public void setSubSpecalityCode2(String subSpecalityCode2) {
		SubSpecalityCode2 = subSpecalityCode2;
	}

	public String getSubSpecalityCode3() {
		return SubSpecalityCode3;
	}

	public void setSubSpecalityCode3(String subSpecalityCode3) {
		SubSpecalityCode3 = subSpecalityCode3;
	}

	//
	// getters / setters
	//
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSourcesystemid() {
		return this.sourcesystemid;
	}

	public void setSourcesystemid(String sourcesystemid) {
		this.sourcesystemid = sourcesystemid;
	}

	public String getSourcesystem() {
		return this.sourcesystemid;
	}

	public void setSourcesystem(String sourcesystem) {
		this.sourcesystem = sourcesystem;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSpecality() {
		return this.specality;
	}

	public void setSpecality(String specality) {
		this.specality = specality;
	}

	public String getSubspecality() {
		return this.subspecality;
	}

	public void setSubspecality(String subspecality) {
		this.subspecality = subspecality;
	}

	public String getClinicalintrest() {
		return this.clinicalintrest;
	}

	public void setClinicalintrest(String clinicalintrest) {
		this.clinicalintrest = clinicalintrest;
	}

	public String getExperience() {
		return this.experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getPhotourl() {
		return this.photourl;
	}

	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}

	public String getSourceurl() {
		return this.sourceurl;
	}

	public void setSourceurl(String sourceurl) {
		this.sourceurl = sourceurl;
	}

	public String getCreatets() {
		return this.createts;
	}

	public void setCreatets(String createts) {
		this.createts = createts;
	}

	public String getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getEffectiveStart() {
		return this.effectiveStart;
	}

	public void setEffectiveStart(String effectiveStart) {
		this.effectiveStart = effectiveStart;
	}

	public String getEffectiveEnd() {
		return this.effectiveEnd;
	}

	public void setEffectiveEnd(String effectiveEnd) {
		this.effectiveEnd = effectiveEnd;
	}

	public String toCSV() {
		StringBuffer buffer = new StringBuffer("");

		// buffer.append("\"").append(id).append("\",");
		buffer.append("\"").append(sourcesystemid).append("\",");
		buffer.append("\"").append(sourcesystem).append("\",");
		buffer.append("\"").append(name).append("\",");
		buffer.append("\"").append(firstname).append("\",");
		buffer.append("\"").append(lastname).append("\",");
		buffer.append("\"").append(gender).append("\",");
		// buffer.append("\"").append(age).append("\",");
		buffer.append("\"").append(street).append("\",");
		buffer.append("\"").append(city.trim()).append("\",");
		buffer.append("\"").append(state).append("\",");
		buffer.append("\"").append(postalcode).append("\",");
		buffer.append("\"").append(country).append("\",");
		buffer.append("\"").append(effectiveStart).append("\",");
		buffer.append("\"").append(effectiveEnd).append("\",");
		buffer.append("\"").append(birthDate).append("\"");

		// buffer.append("\"").append(specality).append("\",");
		// buffer.append("\"").append(subspecality).append("\",");
		// buffer.append("\"").append(clinicalintrest).append("\",");
		// buffer.append("\"").append(experience).append("\",");
		// buffer.append("\"").append(photourl).append("\",");
		// buffer.append("\"").append(sourceurl).append("\",");
		// buffer.append("\"").append(createts).append("\",");

		return buffer.toString();
	}

	public String toRoleCSV() {
		StringBuffer buffer = new StringBuffer("");

		// buffer.append("\"").append(id).append("\",");
		buffer.append("\"").append(sourcesystemid).append("\",");
		buffer.append("\"").append(sourcesystem).append("\",");
		buffer.append("\"").append(specality).append("\",");
		buffer.append("\"").append(specalityCode).append("\",");
		buffer.append("\"").append(SubSpecality1).append("\",");
		buffer.append("\"").append(SubSpecalityCode1).append("\",");
		buffer.append("\"").append(SubSpecality2).append("\",");
		buffer.append("\"").append(SubSpecalityCode2).append("\",");
		buffer.append("\"").append(SubSpecality3).append("\",");
		buffer.append("\"").append(SubSpecalityCode3).append("\",");
		buffer.append("\"").append(sourcesystemid).append("\",");
		buffer.append("\"").append(effectiveStart).append("\",");
		buffer.append("\"").append(effectiveEnd).append("\"");

		// buffer.append("\"").append(specality).append("\",");
		// buffer.append("\"").append(subspecality).append("\",");
		// buffer.append("\"").append(clinicalintrest).append("\",");
		// buffer.append("\"").append(experience).append("\",");
		// buffer.append("\"").append(photourl).append("\",");
		// buffer.append("\"").append(sourceurl).append("\",");
		// buffer.append("\"").append(createts).append("\",");

		return buffer.toString();
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer("").append(".").append("Practitioner").append("(");

		buffer.append("[").append("id").append("=").append(id).append("]");
		buffer.append("[").append("sourcesystemid").append("=").append(sourcesystemid).append("]");
		buffer.append("[").append("name").append("=").append(name).append("]");
		buffer.append("[").append("firstname").append("=").append(firstname).append("]");
		buffer.append("[").append("lastname").append("=").append(lastname).append("]");
		buffer.append("[").append("gender").append("=").append(gender).append("]");
		buffer.append("[").append("age").append("=").append(age).append("]");
		buffer.append("[").append("street").append("=").append(street).append("]");
		buffer.append("[").append("city").append("=").append(city).append("]");
		buffer.append("[").append("state").append("=").append(state).append("]");
		buffer.append("[").append("postalcode").append("=").append(postalcode).append("]");
		buffer.append("[").append("country").append("=").append(country).append("]");
		buffer.append("[").append("specality").append("=").append(specality).append("]");
		buffer.append("[").append("subspecality").append("=").append(subspecality).append("]");
		buffer.append("[").append("clinicalintrest").append("=").append(clinicalintrest).append("]");
		buffer.append("[").append("experience").append("=").append(experience).append("]");
		buffer.append("[").append("photourl").append("=").append(photourl).append("]");
		buffer.append("[").append("sourceurl").append("=").append(sourceurl).append("]");
		buffer.append("[").append("createts").append("=").append(createts).append("]");

		return buffer.append(")").toString();
	}
}
