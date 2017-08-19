package com.salesforce.industries.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.salesforce.industries.core.DBUtil;
import com.salesforce.industries.exception.DAOException;
import com.salesforce.industries.persistence.MedicalschoolDAO;
import com.salesforce.industries.persistence.model.Medicalschool;

@Repository
public class MedicalschoolDAOImpl implements MedicalschoolDAO
{
    //
    // static data
    //
    protected static List<String> pkColumns = new ArrayList<String>();
    protected static List<String> stdColumns = new ArrayList<String>();
    protected static List<String> allColumns = new ArrayList<String>();
    protected static String tableName = "MedicalSchool";

    static
    {
        pkColumns.add("id");
        stdColumns.add("Name");
        stdColumns.add("Certification");
        allColumns.addAll(pkColumns);
        allColumns.addAll(stdColumns);
    }

    @Autowired
    private DataSource mockDataSource;


    public List<Medicalschool> selectAll() throws DAOException
    {
        List<Medicalschool> ret = new ArrayList<Medicalschool>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection=null;

        try
        {
            connection = mockDataSource.getConnection();
            ps = connection.prepareStatement(DBUtil.select(tableName, allColumns));
            rs = ps.executeQuery();

            while (rs.next())
                ret.add(fromResultSet(rs));
        }
        catch (SQLException e)
        {
            throw new DAOException(e);
        }
        finally
        {
            DBUtil.close(connection,ps, rs);
        }

        return ret;
    }

    protected Medicalschool fromResultSet(ResultSet rs) throws SQLException
    {
        Medicalschool obj = new Medicalschool();

        obj.setId(DBUtil.getInt(rs, "id"));
        obj.setName(DBUtil.getString(rs, "Name"));
        obj.setCertification(DBUtil.getString(rs, "Certification"));

        return obj;
    }

}
