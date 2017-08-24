package org.stg.pojo;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.stg.core.Consts;
import org.stg.core.OrgUtils;

public class IndustriesTask extends PojoBase {
    final static Logger logger = Logger.getLogger(IndustriesTask.class);


    String id;
    String subject;
    String accountId;
    String activityDate;
    String goalId;
    String carePlanId;
    String assignedToId;
    String problemId;
    String status;
    String isRecurrence;
    String recurrenceType;
    String recurrenceInterval;
    Calendar recurrenceStartDateOnly;
    Calendar recurrenceEndDateOnly;
    String recurrenceDayOfWeekMask;
    String assignedToRole;

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

    public String getIsRecurrence() {
        return isRecurrence;
    }
    public String getRecurrenceType() {
        return recurrenceType;
    }
    public String getRecurrenceInterval() {
        return recurrenceInterval;
    }
    public void setIsRecurrence(String isRecurrence) {
        this.isRecurrence = isRecurrence;
    }
    public void setRecurrenceType(String recurrenceType) {
        this.recurrenceType = recurrenceType;
    }
    public void setRecurrenceInterval(String recurrenceInterval) {
        this.recurrenceInterval = recurrenceInterval;
    }


    public String getRecurrenceStartDateOnly() {
        if(recurrenceStartDateOnly == null) {
            return "";
        }else {
            String returnValue = Consts.SFDC_DATEFMT.format(recurrenceStartDateOnly.getTime());
            return returnValue;
        }
    }
    public Calendar getRecurrenceStartDateOnlyCalendar() {
        return recurrenceStartDateOnly;
    }


    public void setRecurrenceStartDateOnly(Calendar recurrenceStartDateOnly) {
        this.recurrenceStartDateOnly = recurrenceStartDateOnly;
    }

    public Calendar getRecurrenceEndDateOnlyCalendar() {
        return recurrenceEndDateOnly;
    }

    public String getRecurrenceEndDateOnly() {
        if(recurrenceEndDateOnly == null) {
            return "";
        }else {
            String returnValue = Consts.SFDC_DATEFMT.format(recurrenceEndDateOnly.getTime());
            return returnValue;
        }
    }
    public void setRecurrenceEndDateOnly(Calendar recurrenceEndDateOnly) {
        this.recurrenceEndDateOnly = recurrenceEndDateOnly;
    }
    public String getRecurrenceDayOfWeekMask() {
        return recurrenceDayOfWeekMask;
    }
    public void setRecurrenceDayOfWeekMask(String recurrenceDayOfWeekMask) {
        this.recurrenceDayOfWeekMask = recurrenceDayOfWeekMask;
    }
    public String getAssignedToRole() {
        return assignedToRole;
    }
    public void setAssignedToRole(String assignedToRole) {
        this.assignedToRole = assignedToRole;
    }
    @Override
    public String toString() {
        return "IndustriesTask [id=" + id + ", subject=" + subject + ", accountId=" + accountId + ", activityDate="
                + activityDate + ", goalId=" + goalId + ", carePlanId=" + carePlanId + ", assignedToId=" + assignedToId
                + ", problemId=" + problemId + ", status=" + status + "]";
    }

    public String getCSVHeader(String nameSpace) {
        return "Subject,ActivityDate,Status,OwnerId,WhatId,"+OrgUtils.getFQFieldName(nameSpace,"CarePlanProblem__c")+","+
                OrgUtils.getFQFieldName(nameSpace,"CarePlanGoal__c")+",IsRecurrence,RecurrenceType,RecurrenceInterval,RecurrenceStartDateOnly,RecurrenceEndDateOnly,RECURRENCEDAYOFWEEKMASK";
    }

    public String getObjectName(String nameSpace) {
        return Consts.SOBJECT_TASK;
    }

    @Override
    public String toCSVString() {
        validateDate(this);
        return "\"" + subject + "\"," + activityDate + "," + status + "," + assignedToId + "," +  carePlanId + "," + problemId + "," +
        goalId+","+isRecurrence+","+recurrenceType+","+recurrenceInterval+","+getRecurrenceStartDateOnly()+","+getRecurrenceEndDateOnly()
        +","+recurrenceDayOfWeekMask;
    }
    @SuppressWarnings("unused")
    private static long daysBetween(Calendar startDate, Calendar endDate) {
        long end = endDate.getTimeInMillis();
        long start = startDate.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(end - start);
    }

    private void validateDate (IndustriesTask task) {
        Calendar startDate = task.getRecurrenceStartDateOnlyCalendar();
        Calendar endDate = task.getRecurrenceEndDateOnlyCalendar();
        if(startDate != null) {
            @SuppressWarnings("unused")
            Boolean error=false;
            long end = endDate.getTimeInMillis();
            long start = startDate.getTimeInMillis();
            if(end-start < 1) {
                System.out.println("Here");
            }
            if(TimeUnit.MILLISECONDS.toDays(Math.abs(end - start)) > 100) {
                error = true;
            }
            if(end < start) {
                error = true;
            }
        }
    }
}
