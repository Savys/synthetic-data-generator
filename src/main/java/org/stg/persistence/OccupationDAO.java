package org.stg.persistence;
import java.util.List;

import org.stg.exception.DAOException;
import org.stg.persistence.model.Occupation;

public interface OccupationDAO {
    public List<Occupation> selectAll() throws DAOException;
    public List<Occupation> selectWithSalary() throws DAOException;

}
