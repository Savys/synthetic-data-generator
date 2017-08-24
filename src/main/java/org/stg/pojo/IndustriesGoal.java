package org.stg.pojo;

import java.util.List;

import org.stg.core.Consts;
import org.stg.core.OrgUtils;

public class IndustriesGoal extends PojoBase {

    @Override
    public String toString() {
        return "IndustriesGoal [id=" + id + ", name=" + name + ", problemId=" + problemId + ", carePlanId=" + carePlanId
                + ", tasks=" + tasks + ", ccUser=" + ccUser + "]";
    }

    public String getCSVHeader(String nameSpace) {
        return "\"NAME\","+OrgUtils.getFQFieldName(nameSpace,"CAREPLAN__C")+","+OrgUtils.getFQFieldName(nameSpace,"CarePlanProblem__c");
    }

    public String getObjectName(String nameSpace) {
        return nameSpace+"__"+Consts.SOBJECT_GOAL;
    }

    public String toCSVString() {
        return "\"" + name + "\"," + carePlanId + "," + problemId + "";
    }

    String id;
    String name;
    String problemId;
    String carePlanId;
    List<IndustriesTask> tasks;

    private String ccUser;

    public String getCcUser() {
        return ccUser;
    }
    public void setCcUser(String ccUser) {
        this.ccUser = ccUser;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getProblemId() {
        return problemId;
    }
    public List<IndustriesTask> getTasks() {
        return tasks;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }
    public void setTasks(List<IndustriesTask> tasks) {
        this.tasks = tasks;
    }
    public String getCarePlanId() {
        return carePlanId;
    }
    public void setCarePlanId(String carePlanId) {
        this.carePlanId = carePlanId;
    }
}
