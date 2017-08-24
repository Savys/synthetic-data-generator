package org.stg.pojo;

import java.util.List;

import org.stg.core.Consts;
import org.stg.core.OrgUtils;

public class IndustriesProblem extends PojoBase {

    @Override
    public String toString() {
        return "IndustriesProblem [id=" + id + ", name=" + name + ", carePlanId=" + carePlanId + ", goals=" + goals
                + ", ccUser=" + ccUser + "]";
    }

    public String getCSVHeader(String nameSpace) {
        return "\"NAME\","+OrgUtils.getFQFieldName(nameSpace,"CAREPLAN__C");
    }

    public String getObjectName(String nameSpace) {
        return nameSpace+"__"+Consts.SOBJECT_PROBLEM;
    }


    @Override
    public String toCSVString() {
        return "\""+name.trim() + "\""+"," + carePlanId;
    }
    String id;
    String name;
    String carePlanId;
    List<IndustriesGoal> goals;
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
    public String getCarePlanId() {
        return carePlanId;
    }
    public List<IndustriesGoal> getGoals() {
        return goals;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCarePlanId(String carePlanId) {
        this.carePlanId = carePlanId;
    }
    public void setGoals(List<IndustriesGoal> goals) {
        this.goals = goals;
    }
}
