package com.salesforce.industries.pojo;

import com.salesforce.industries.core.Consts;
import com.salesforce.industries.persistence.model.CarePlan;
import com.salesforce.industries.pojo.PojoBase;

public class IndustriesCarePlan extends PojoBase{

    private String accountId;
    private String contactId;
    private String recordTypeId;
    private String subject;
    private Boolean isPrimary;
    private String id;
    private String ccUser;
    private String referenceId;

    public IndustriesCarePlan(CarePlan carePlan) {
        this.referenceId = carePlan.getId().toString();
        this.subject = carePlan.getSubject();
    }
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getContactId() {
        return contactId;
    }
    public void setContactId(String contactId) {
        this.contactId = contactId;
    }
    public String getRecordTypeId() {
        return recordTypeId;
    }
    public void setRecordTypeId(String recordTypeId) {
        this.recordTypeId = recordTypeId;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public Boolean getIsPrimary() {
        return isPrimary;
    }
    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCcUser() {
        return ccUser;
    }
    public void setCcUser(String ccUser) {
        this.ccUser = ccUser;
    }
    @Override
    public String getCSVHeader(String nameSpace) {
        return "\"ACCOUNTID\",\"CONTACTID\",\"RECORDTYPEID\",\"SUBJECT\"";
    }
    @Override
    public String getObjectName(String nameSpace) {
        return Consts.SOBJECT_CASE;
    }
    public String getReferenceId() {
        return referenceId;
    }
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
    @Override
    public String toCsvString() {
        return accountId + "," + contactId + "," + recordTypeId+ "," + subject;
    }

}
