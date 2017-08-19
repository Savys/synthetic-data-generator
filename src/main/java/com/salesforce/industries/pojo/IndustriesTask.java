package com.salesforce.industries.pojo;

import com.salesforce.industries.core.Consts;
import com.salesforce.industries.core.OrgUtils;

public class IndustriesTask extends PojoBase {

    String id;
    String subject;
    String accountId;
    String activityDate;
    String goalId;
    String carePlanId;
    String assignedToId;
    String problemId;
    String status;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getId() {
        return id;
    }
    public String getSubject() {
        return subject;
    }
    public String getAccountId() {
        return accountId;
    }
    public String getActivityDate() {
        return activityDate;
    }
    public String getGoalId() {
        return goalId;
    }
    public String getCarePlanId() {
        return carePlanId;
    }
    public String getAssignedToId() {
        return assignedToId;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }
    public void setGoalId(String goalId) {
        this.goalId = goalId;
    }
    public void setCarePlanId(String carePlanId) {
        this.carePlanId = carePlanId;
    }
    public void setAssignedToId(String assignedToId) {
        this.assignedToId = assignedToId;
    }
    public String getProblemId() {
        return problemId;
    }
    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }


    @Override
    public String toString() {
        return "IndustriesTask [id=" + id + ", subject=" + subject + ", accountId=" + accountId + ", activityDate="
                + activityDate + ", goalId=" + goalId + ", carePlanId=" + carePlanId + ", assignedToId=" + assignedToId
                + ", problemId=" + problemId + ", status=" + status + "]";
    }

    public String getCSVHeader(String nameSpace) {
        return "Subject,ActivityDate,Status,OwnerId,WhatId,"+OrgUtils.getFQFieldName(nameSpace,"CarePlanProblem__c")+","+
                OrgUtils.getFQFieldName(nameSpace,"CarePlanGoal__c");
    }

    public String getObjectName(String nameSpace) {
        return Consts.SOBJECT_TASK;
    }

    @Override
    public String toCsvString() {
        return "\"" + subject + "\"," + activityDate + "," + status + "," + assignedToId + "," +  carePlanId + "," + problemId + "," + goalId;
    }
}
