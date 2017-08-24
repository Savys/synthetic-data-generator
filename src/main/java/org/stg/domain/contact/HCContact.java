package org.stg.domain.contact;

import org.stg.core.Consts;
import org.stg.core.OrgUtils;

public class HCContact extends IndustriesContact{
	String medicalRecordNumber;
    String sourceSystemId;
    String sourceSystem = "EMR1";

    public String getSourceSystem() {
        return "EMR1";
    }

    public String getSourceSystemId() {
        return sourceSystemId;
    }

    public void setSourceSystemId(String sourceSystemId) {
        this.sourceSystemId = sourceSystemId;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

	public String getMedicalRecordNumber() {
		return medicalRecordNumber;
	}

	public void setMedicalRecordNumber(String medicalRecordNumber) {
		this.medicalRecordNumber = medicalRecordNumber;
	}

    @Override
    public String getCSVHeader(String nameSpace) {
        String parentHeader = super.getCSVHeader(nameSpace);
        return parentHeader + "," +
                    OrgUtils.getFQFieldName(nameSpace,"MedicalRecordNumber__c")+","+
                    OrgUtils.getFQFieldName(nameSpace,"SourceSystemId__c")+"," +
                    OrgUtils.getFQFieldName(nameSpace,"SourceSystem__c");
    }

    @Override
    public String getObjectName(String nameSpace) {
        return Consts.SOBJECT_CONTACT;
    }

    @Override
    public String toCSVString() {
        String parentRecord = super.toCSVString();
        return parentRecord +  "," + medicalRecordNumber  + "," + sourceSystemId + "," + sourceSystem;
    }

}
