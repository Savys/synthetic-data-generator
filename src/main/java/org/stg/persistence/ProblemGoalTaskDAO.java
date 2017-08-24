package org.stg.persistence;
import java.util.List;
import java.util.Map;

import org.stg.exception.DAOException;
import org.stg.persistence.model.Problem;
import org.stg.persistence.model.Task;



public interface ProblemGoalTaskDAO {
    public Map<Integer,List<Task>> selectAllUnrelatedCarePlanTasks() throws DAOException;
    public Map<Integer,List<Problem>>  selectAll() throws DAOException;

}
