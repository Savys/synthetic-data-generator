package com.salesforce.industries.pojo;

public class LoginProperty {

    String userName;
    String password;
    String endPoint;
    String loginEndPoint;

    public String getLoginEndPoint() {
        return loginEndPoint;
    }
    public void setLoginEndPoint(String loginEndPoint) {
        this.loginEndPoint = loginEndPoint;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEndPoint() {
        return endPoint;
    }
    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

}
