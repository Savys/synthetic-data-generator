package org.stg.persistence.model;

public class Occupation {
    Long id;
    String SOCNumber;
    String name;
    Double annualizedSalary;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Double getAnnualizedSalary() {
        return annualizedSalary;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAnnualizedSalary(Double annualizedSalary) {
        this.annualizedSalary = annualizedSalary;
    }
    public String getSOCNumber() {
        return SOCNumber;
    }
    public void setSOCNumber(String sOCNumber) {
        SOCNumber = sOCNumber;
    }

}
