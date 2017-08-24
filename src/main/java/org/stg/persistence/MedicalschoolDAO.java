package org.stg.persistence;

import java.util.List;

import org.stg.exception.DAOException;
import org.stg.persistence.model.Medicalschool;


public interface MedicalschoolDAO
{
    public List<Medicalschool> selectAll() throws DAOException;

}
