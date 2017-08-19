package com.salesforce.industries.pojo;

public class User extends PojoBase {

    String Id;
    String username;
    String UserRoleId;
    String UserType;
    String Email;
    String IsActive;
    String roleName;
    String accountId;
    String contactId;

    public String getId() {
        return Id;
    }
    public void setId(String id) {
        Id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserRoleId() {
        return UserRoleId;
    }
    public void setUserRoleId(String userRoleId) {
        UserRoleId = userRoleId;
    }
    public String getUserType() {
        return UserType;
    }
    public void setUserType(String userType) {
        UserType = userType;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public String getIsActive() {
        return IsActive;
    }
    public void setIsActive(String isActive) {
        IsActive = isActive;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
    @Override
    public String getCSVHeader(String nameSpace) {
        return null;
    }
    @Override
    public String getObjectName(String nameSpace) {
        return null;
    }
    @Override
    public String toCsvString() {
        return null;
    }
}
