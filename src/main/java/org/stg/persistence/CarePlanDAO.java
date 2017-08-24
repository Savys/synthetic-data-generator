package org.stg.persistence;
import java.util.List;

import org.stg.exception.DAOException;
import org.stg.persistence.model.CarePlan;



public interface CarePlanDAO {
    public List<CarePlan> selectAll() throws DAOException;

}
