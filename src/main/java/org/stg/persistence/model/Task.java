package org.stg.persistence.model;

public class Task extends BaseModel{

    Integer taskId;
    String taskText;
    String isRecurrence;
    String recurrenceType;
    String recurrenceInterval;
    public String getAssignedToRole() {
        return assignedToRole;
    }
    public void setAssignedToRole(String assignedToRole) {
        this.assignedToRole = assignedToRole;
    }
    String assignedToRole;

    @Override
    public String toString() {
        return "Task [taskId=" + taskId + ", taskText=" + taskText + ", isRecurrence=" + isRecurrence
                + ", recurrenceType=" + recurrenceType + ", recurrenceInterval=" + recurrenceInterval + "]";
    }
    public Integer getTaskId() {
        return taskId;
    }
    public String getTaskText() {
        return taskText;
    }
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
    public void setTaskText(String taskText) {
        this.taskText = taskText;
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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
        result = prime * result + ((taskText == null) ? 0 : taskText.hashCode());
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
        Task other = (Task) obj;
        if (taskId == null) {
            if (other.taskId != null)
                return false;
        } else if (!taskId.equals(other.taskId))
            return false;
        if (taskText == null) {
            if (other.taskText != null)
                return false;
        } else if (!taskText.equals(other.taskText))
            return false;
        return true;
    }


}
