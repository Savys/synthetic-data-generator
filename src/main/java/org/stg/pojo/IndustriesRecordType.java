package org.stg.pojo;

public class IndustriesRecordType {

    @Override
    public String toString() {
        return "IndustriesRecordType [RecordTypeName=" + RecordTypeName + ", sObjectType=" + sObjectType
                + ", RecordTypeId=" + RecordTypeId + ", nameSpace=" + nameSpace + "]";
    }
    private String RecordTypeName;
    private String sObjectType;
    private String RecordTypeId;
    private String nameSpace;

    public String getRecordTypeName() {
        return RecordTypeName;
    }
    public void setRecordTypeName(String recordTypeName) {
        RecordTypeName = recordTypeName;
    }
    public String getsObjectType() {
        return sObjectType;
    }
    public void setsObjectType(String sObjectType) {
        this.sObjectType = sObjectType;
    }
    public String getRecordTypeId() {
        return RecordTypeId;
    }
    public void setRecordTypeId(String recordTypeId) {
        RecordTypeId = recordTypeId;
    }
    public String getNameSpace() {
        return nameSpace;
    }
    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

}
