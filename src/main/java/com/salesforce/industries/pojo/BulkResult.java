package com.salesforce.industries.pojo;

public class BulkResult {

    String Id;
    Boolean success;
    Boolean created;
    String error;

    public String getId() {
        return Id;
    }
    public void setId(String id) {
        Id = id;
    }
    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public Boolean getCreated() {
        return created;
    }
    public void setCreated(Boolean created) {
        this.created = created;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }

}
