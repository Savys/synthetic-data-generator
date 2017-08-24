package org.stg.core;

public class OrgUtils {

    public static String getFQFieldName(String nameSpace,String fieldName) {
        if(fieldName.toLowerCase().contains("__c") && nameSpace.trim().length() > 0) {
            return nameSpace+"__"+fieldName;
        }else {
            return fieldName;
        }

    }
}
