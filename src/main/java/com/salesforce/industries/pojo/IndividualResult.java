package com.salesforce.industries.pojo;

import java.util.Set;

public class IndividualResult {

    @Override
    public String toString() {
        return "IndividualResult [accountId=" + accountId + ", contactId=" + contactId + ", caseIds=" + caseIds + "]";
    }
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getContactId() {
        return contactId;
    }
    public void setContactId(String contactId) {
        this.contactId = contactId;
    }
    public Set<String> getCaseIds() {
        return caseIds;
    }
    public void setCaseIds(Set<String> caseIds) {
        this.caseIds = caseIds;
    }
    String accountId;
    String contactId;
    Set<String> caseIds;
}
