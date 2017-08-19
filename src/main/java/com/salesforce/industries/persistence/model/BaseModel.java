package com.salesforce.industries.persistence.model;

import com.salesforce.industries.core.BaseDataItem;

public class BaseModel extends BaseDataItem{

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((referenceId == null) ? 0 : referenceId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseModel other = (BaseModel) obj;
        if (referenceId == null) {
            if (other.referenceId != null)
                return false;
        } else if (!referenceId.equals(other.referenceId))
            return false;
        return true;
    }

    private Integer referenceId;

    public Integer getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Integer referenceId) {
        this.referenceId = referenceId;
    }

}
