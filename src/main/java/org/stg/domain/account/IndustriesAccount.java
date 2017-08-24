package org.stg.domain.account;

import org.stg.core.Consts;

public class IndustriesAccount extends Account{
	String primaryContactId = new String();

	public String getPrimaryContactId() {
		return primaryContactId;
	}

	public void setPrimaryContactId(String primaryContactId) {
		this.primaryContactId = primaryContactId;
	}

    @Override
    public String getCSVHeader(String nameSpace) {
        String parent = super.getCSVHeader(nameSpace);
        return parent;
    }

    @Override
    public String getObjectName(String nameSpace) {
        return Consts.SOBJECT_ACCOUNT;
    }

    @Override
    public String toCSVString() {
        String parent = super.toCSVString();
        return parent;
    }
}
