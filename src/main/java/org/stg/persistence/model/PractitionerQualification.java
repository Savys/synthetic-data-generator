package org.stg.persistence.model;

public class PractitionerQualification extends BaseModel{

	private String name;
	private String issue;
	private String sourceSystemId;
	private String sourceSystem;
	private String typeCode;
	private String typeLabel;

	private String effectiveStart;
	private String effectiveEnd;

	private String practitionerId;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
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

	public String getEffectiveStart() {
		return effectiveStart;
	}

	public void setEffectiveStart(String effectiveStart) {
		this.effectiveStart = effectiveStart;
	}

	public String getEffectiveEnd() {
		return effectiveEnd;
	}

	public void setEffectiveEnd(String effectiveEnd) {
		this.effectiveEnd = effectiveEnd;
	}

	public String getPractitionerId() {
		return practitionerId;
	}

	public void setPractitionerId(String practitionerId) {
		this.practitionerId = practitionerId;
	}

	public String toCSV() {
		StringBuffer buffer = new StringBuffer("");

		buffer.append("\"").append(sourceSystemId).append("\",");
		buffer.append("\"").append(sourceSystem).append("\",");
		//buffer.append("\"").append(name).append("\",");
		buffer.append("\"").append(issue).append("\",");
		buffer.append("\"").append(typeCode).append("\",");
		buffer.append("\"").append(typeLabel).append("\",");
		buffer.append("\"").append(practitionerId).append("\",");
		//buffer.append("\"").append(effectiveStart).append("\",");
		buffer.append("\"").append(effectiveEnd).append("\"");

		return buffer.toString();
	}

}
