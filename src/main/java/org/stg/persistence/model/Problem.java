package org.stg.persistence.model;

import java.util.List;

public class Problem extends BaseModel {

    @Override
    public String toString() {
        return "Problem [problemId=" + problemId + ", problemText=" + problemText + ", carePlanId=" + carePlanId
                + ", \n goals=" + goals + "]\n";
    }
    Integer problemId;
    String problemText;
    Integer carePlanId;
    List<Goal> goals;

    public Integer getCarePlanId() {
        return carePlanId;
    }
    public void setCarePlanId(Integer carePlanId) {
        this.carePlanId = carePlanId;
    }
    public Integer getProblemId() {
        return problemId;
    }
    public String getProblemText() {
        return problemText;
    }
    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }
    public void setProblemText(String problemText) {
        this.problemText = problemText;
    }
    public List<Goal> getGoals() {
        return goals;
    }
    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((carePlanId == null) ? 0 : carePlanId.hashCode());
        result = prime * result + ((goals == null) ? 0 : goals.hashCode());
        result = prime * result + ((problemId == null) ? 0 : problemId.hashCode());
        result = prime * result + ((problemText == null) ? 0 : problemText.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Problem other = (Problem) obj;
        if (carePlanId == null) {
            if (other.carePlanId != null)
                return false;
        } else if (!carePlanId.equals(other.carePlanId))
            return false;
        if (goals == null) {
            if (other.goals != null)
                return false;
        } else if (!goals.equals(other.goals))
            return false;
        if (problemId == null) {
            if (other.problemId != null)
                return false;
        } else if (!problemId.equals(other.problemId))
            return false;
        if (problemText == null) {
            if (other.problemText != null)
                return false;
        } else if (!problemText.equals(other.problemText))
            return false;
        return true;
    }


}
