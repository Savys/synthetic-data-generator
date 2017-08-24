package org.stg.persistence;

import java.util.List;

import org.stg.exception.DAOException;
import org.stg.persistence.model.LastName;


public interface LastNameDAO
{
    public List<LastName> selectAll() throws DAOException;

}
