package com.salesforce.industries.persistence;

import com.salesforce.industries.persistence.impl.AddressDAOImpl;
import com.salesforce.industries.persistence.impl.FirstNameDAOImpl;
import com.salesforce.industries.persistence.impl.LastNameDAOImpl;
import com.salesforce.industries.persistence.impl.MedicalschoolDAOImpl;
import com.salesforce.industries.persistence.impl.PractitionerDAOImpl;

public class JDBCDAOFactory extends DAOFactory
{
    public PractitionerDAO getPractitionerDAO()
    {
        return new PractitionerDAOImpl();
    }

    public MedicalschoolDAO getMedicalschoolDAO()
    {
        return new MedicalschoolDAOImpl();
    }

    public AddressDAO getAddressDAO() {
        return new AddressDAOImpl();
    }

    public FirstNameDAO getFirstnamesDAO()
    {
        return new FirstNameDAOImpl();
    }

    public LastNameDAO getLastnamesDAO()
    {
        return new LastNameDAOImpl();
    }
}

