package org.stg.pojo;

public class MockDataSourceProperty {
    public String getDbHost() {
        return dbHost;
    }
    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }
    public String getDbName() {
        return dbName;
    }
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    public String getDbUserName() {
        return dbUserName;
    }
    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }
    public String getDbPassword() {
        return dbPassword;
    }
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }
    public String getDriver() {
        return driver;
    }
    public void setDriver(String driver) {
        this.driver = driver;
    }
    private  String dbHost;
    private  String dbName;
    private  String dbUserName;
    private  String dbPassword;
    private  String driver;

}
