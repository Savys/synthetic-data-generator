package org.stg.domain.account;

import org.stg.core.Consts;
import org.stg.core.OrgUtils;

public class HCAccount extends IndustriesAccount{

    String sourceSystemId;
    String sourceSystem;
	String medicalRecordNumber;

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

	public String getMedicalRecordNumber() {
		return medicalRecordNumber;
	}

	public void setMedicalRecordNumber(String medicalRecordNumber) {
		this.medicalRecordNumber = medicalRecordNumber;
	}

    @Override
    public String getCSVHeader(String nameSpace) {
        String parent = super.getCSVHeader(nameSpace);
        return parent + "," + OrgUtils.getFQFieldName(nameSpace,"MedicalRecordNumber__c")+","+OrgUtils.getFQFieldName(nameSpace,"SourceSystemId__c")+","+OrgUtils.getFQFieldName(nameSpace,"SourceSystem__c");
    }

    @Override
    public String getObjectName(String nameSpace) {
        return Consts.SOBJECT_ACCOUNT;
    }

    @Override
    public String toCSVString() {
        String parent = super.toCSVString();
        return parent + "," + getMedicalRecordNumber() + ","+ getSourceSystemId() +"," + getSourceSystemId();
    }


}
