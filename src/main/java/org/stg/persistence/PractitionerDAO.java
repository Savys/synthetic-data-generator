package org.stg.persistence;

import java.util.List;

import org.stg.exception.DAOException;
import org.stg.persistence.model.Practitioner;


public interface PractitionerDAO
{
    public List<Practitioner> selectAll() throws DAOException;
}
