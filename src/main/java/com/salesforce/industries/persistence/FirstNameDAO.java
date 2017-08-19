package com.salesforce.industries.persistence;

import java.util.List;

import com.salesforce.industries.exception.DAOException;
import com.salesforce.industries.persistence.model.FirstName;


public interface FirstNameDAO
{
    public List<FirstName> selectAll() throws DAOException;
}