package com.salesforce.industries.pojo;

import com.salesforce.industries.core.BaseDataItem;

public abstract class PojoBase extends BaseDataItem {
    public abstract String getCSVHeader(String nameSpace);
    public abstract String getObjectName(String nameSpace);
    public abstract String toCsvString();
}
