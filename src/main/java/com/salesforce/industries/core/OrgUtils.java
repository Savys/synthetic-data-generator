package com.salesforce.industries.core;

public class OrgUtils {

    public static String getFQFieldName(String nameSpace,String fieldName) {
        if(fieldName.toLowerCase().contains("__c") ) {
            return nameSpace+"__"+fieldName;
        }else {
            return fieldName;
        }

    }
}
