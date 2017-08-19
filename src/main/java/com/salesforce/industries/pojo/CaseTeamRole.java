package com.salesforce.industries.pojo;

public class CaseTeamRole  extends PojoBase {
    String Name;
    String AccessLevel;
    String Id;

    public String getName() {
        return Name;
    }
    public String getAccessLevel() {
        return AccessLevel;
    }
    public String getId() {
        return Id;
    }
    public void setName(String name) {
        Name = name;
    }
    public void setAccessLevel(String accessLevel) {
        AccessLevel = accessLevel;
    }
    public void setId(String id) {
        Id = id;
    }
    @Override
    public String getCSVHeader(String nameSpace) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public String getObjectName(String nameSpace) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public String toCsvString() {
        // TODO Auto-generated method stub
        return null;
    }


}
