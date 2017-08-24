package org.stg.persistence.model;

import java.util.List;

public class Goal extends BaseModel{

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((goalId == null) ? 0 : goalId.hashCode());
        result = prime * result + ((goalText == null) ? 0 : goalText.hashCode());
        result = prime * result + ((tasks == null) ? 0 : tasks.hashCode());
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
        Goal other = (Goal) obj;
        if (goalId == null) {
            if (other.goalId != null)
                return false;
        } else if (!goalId.equals(other.goalId))
            return false;
        if (goalText == null) {
            if (other.goalText != null)
                return false;
        } else if (!goalText.equals(other.goalText))
            return false;
        if (tasks == null) {
            if (other.tasks != null)
                return false;
        } else if (!tasks.equals(other.tasks))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Goal [goalId=" + goalId + ", goalText=" + goalText + "\n, tasks=" + tasks + "]\n";
    }
    public Integer goalId;
    public String goalText;
    List<Task> tasks;

    public Integer getGoalId() {
        return goalId;
    }
    public String getGoalText() {
        return goalText;
    }
    public void setGoalId(Integer goalId) {
        this.goalId = goalId;
    }
    public void setGoalText(String goalText) {
        this.goalText = goalText;
    }
    public List<Task> getTasks() {
        return tasks;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


}
