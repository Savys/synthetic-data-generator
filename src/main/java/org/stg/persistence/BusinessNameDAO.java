package org.stg.persistence;
import java.util.List;

import org.stg.exception.DAOException;
import org.stg.persistence.model.BusinessName;

public interface BusinessNameDAO {
    public List<BusinessName> selectAll() throws DAOException;
    public List<BusinessName> selectFortune(int number) throws DAOException;

}
