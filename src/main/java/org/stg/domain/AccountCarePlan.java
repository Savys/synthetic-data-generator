package org.stg.domain;

import org.stg.core.Consts;
import org.stg.core.OrgUtils;
import org.stg.pojo.PojoBase;

public class AccountCarePlan extends PojoBase {
    String id;
    String primaryCarePlanId;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPrimaryCarePlanId() {
        return primaryCarePlanId;
    }
    public void setPrimaryCarePlanId(String primaryCarePlanId) {
        this.primaryCarePlanId = primaryCarePlanId;
    }
    public String getCSVHeader(String nameSpace) {
        return "Id,"+OrgUtils.getFQFieldName(nameSpace,"CarePlan__c");
    }
    public String getObjectName(String nameSpace) {
        return Consts.SOBJECT_ACCOUNT;
    }
    public String toCSVString() {
        return id + "," + primaryCarePlanId;
    }

}
