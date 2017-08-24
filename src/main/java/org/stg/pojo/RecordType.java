package org.stg.pojo;

public class RecordType {
    private String developerName;
    private String recordTypeId;
    private Boolean isActive;
    private String recordTypeName;
    private String namespacePrefix;
    private String sObjectType;

    public String getDeveloperName() {
        return developerName;
    }
    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }
    public String getRecordTypeId() {
        return recordTypeId;
    }
    public void setRecordTypeId(String recordTypeId) {
        this.recordTypeId = recordTypeId;
    }
    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    public String getRecordTypeName() {
        return recordTypeName;
    }
    public void setRecordTypeName(String recordTypeName) {
        this.recordTypeName = recordTypeName;
    }
    public String getNamespacePrefix() {
        return namespacePrefix;
    }
    public void setNamespacePrefix(String namespacePrefix) {
        this.namespacePrefix = namespacePrefix;
    }
    public String getsObjectType() {
        return sObjectType;
    }
    public void setsObjectType(String sObjectType) {
        this.sObjectType = sObjectType;
    }

}
