package org.stg.pojo;

import org.stg.core.BaseDataItem;

public abstract class PojoBase extends BaseDataItem {
    public abstract String getCSVHeader(String nameSpace);
    public abstract String getObjectName(String nameSpace);
    public abstract String toCSVString();
}
