package com.salesforce.industries.persistence;
import java.util.List;

import com.salesforce.industries.exception.DAOException;
import com.salesforce.industries.persistence.model.CarePlan;



public interface CarePlanDAO {
    public List<CarePlan> selectAll() throws DAOException;

}
