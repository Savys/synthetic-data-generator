package com.salesforce.industries.persistence;

import java.util.List;

import com.salesforce.industries.exception.DAOException;
import com.salesforce.industries.persistence.model.Medicalschool;


public interface MedicalschoolDAO
{
    public List<Medicalschool> selectAll() throws DAOException;

}
