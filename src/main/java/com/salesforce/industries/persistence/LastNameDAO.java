package com.salesforce.industries.persistence;

import java.util.List;

import com.salesforce.industries.exception.DAOException;
import com.salesforce.industries.persistence.model.LastName;


public interface LastNameDAO
{
    public List<LastName> selectAll() throws DAOException;

}
