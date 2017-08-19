package com.salesforce.industries.persistence;

import java.util.List;

import com.salesforce.industries.exception.DAOException;
import com.salesforce.industries.persistence.model.Practitioner;


public interface PractitionerDAO
{
    public List<Practitioner> selectAll() throws DAOException;
}
