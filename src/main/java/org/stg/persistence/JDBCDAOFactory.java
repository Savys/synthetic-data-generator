package org.stg.persistence;

import org.stg.persistence.impl.AddressDAOImpl;
import org.stg.persistence.impl.FirstNameDAOImpl;
import org.stg.persistence.impl.LastNameDAOImpl;
import org.stg.persistence.impl.MedicalschoolDAOImpl;
import org.stg.persistence.impl.PractitionerDAOImpl;

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

