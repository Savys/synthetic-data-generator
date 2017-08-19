package com.salesforce.industries.persistence;
import java.util.List;
import java.util.Map;

import com.salesforce.industries.exception.DAOException;
import com.salesforce.industries.persistence.model.Problem;



public interface ProblemGoalTaskDAO {
    public Map<Integer,List<Problem>>  selectAll() throws DAOException;

}
