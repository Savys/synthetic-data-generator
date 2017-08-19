package com.salesforce.industries.persistence.model;

public class PractitionerIdentity extends BaseModel{

	private String name;
	private String organization;
	private String sourceSystemId;
	private String sourceSystem;
	private String typeCode;
	private String typeLabel;
	private String value;

	private String effectiveStart;
	private String effectiveEnd;

	private String practitionerId;

	public String gePractitionerId() {
		return practitionerId;
	}

	public void setPractitionerId(String practitionerId) {
		this.practitionerId = practitionerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getSourceSystemId() {
		return sourceSystemId;
	}

	public void setSourceSystemId(String sourceSystemId) {
		this.sourceSystemId = sourceSystemId;
	}

	public String getSourceSystem() {
		return sourceSystemId;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeLabel() {
		return typeLabel;
	}

	public void setTypeLabel(String typeLabel) {
		this.typeLabel = typeLabel;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

		buffer.append("\"").append(sourceSystemId).append("\",");
		buffer.append("\"").append(sourceSystem).append("\",");
		buffer.append("\"").append(name).append("\",");
		buffer.append("\"").append(organization).append("\",");
		buffer.append("\"").append(typeCode).append("\",");
		buffer.append("\"").append(typeLabel).append("\",");
		buffer.append("\"").append(value).append("\",");
		buffer.append("\"").append(practitionerId).append("\",");
		buffer.append("\"").append(effectiveStart).append("\",");
		buffer.append("\"").append(effectiveEnd).append("\"");

		return buffer.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
