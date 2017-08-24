package org.stg.persistence;

import java.util.List;

import org.stg.exception.DAOException;
import org.stg.persistence.model.FirstName;


public interface FirstNameDAO
{
    public List<FirstName> selectAll() throws DAOException;
}