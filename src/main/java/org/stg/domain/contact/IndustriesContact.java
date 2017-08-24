package org.stg.domain.contact;

import org.stg.core.Consts;
import org.stg.core.OrgUtils;

public class IndustriesContact extends Contact{

    String gender  = new String ();

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String getCSVHeader(String nameSpace) {
        String parentHeader = super.getCSVHeader(nameSpace);
        return parentHeader+ "," + OrgUtils.getFQFieldName(nameSpace,"Gender__c");
    }

    @Override
    public String getObjectName(String nameSpace) {
        return Consts.SOBJECT_CONTACT;
    }

    @Override
    public String toCSVString() {
        String parentCSV = super.toCSVString();
        return parentCSV+",\""+gender+"\"";
    }
}
