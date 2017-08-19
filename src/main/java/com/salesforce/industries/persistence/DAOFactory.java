package com.salesforce.industries.persistence;

import java.sql.SQLException;

import com.salesforce.industries.exception.DAOException;
import com.salesforce.industries.persistence.AddressDAO;
import com.salesforce.industries.persistence.MedicalschoolDAO;


public abstract class DAOFactory
{
    private static DAOFactory singleton;

    public static DAOFactory getInstance() throws DAOException
    {
        try
        {
            if (null == singleton)
            {
                singleton = (DAOFactory) Class.forName("com.datagenerator.pojo.JDBCDAOFactory").newInstance();
            }
        }
        catch (Exception e)
        {
            throw new DAOException("Could not create the DAOFactory singleton", e);
        }

        return singleton;
    }

    public abstract PractitionerDAO getPractitionerDAO() throws ClassNotFoundException, SQLException;
    public abstract MedicalschoolDAO getMedicalschoolDAO();
    public abstract AddressDAO getAddressDAO();
    public abstract FirstNameDAO getFirstnamesDAO();
    public abstract LastNameDAO getLastnamesDAO();


}
