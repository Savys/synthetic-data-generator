package org.stg.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stg.core.DBUtil;
import org.stg.exception.DAOException;
import org.stg.persistence.PractitionerDAO;
import org.stg.persistence.model.Practitioner;

@Repository
public class PractitionerDAOImpl implements PractitionerDAO
{



    //protected Connection connection = null;
    //
    // static data
    //
    protected static List<String> pkColumns = new ArrayList<String>();
    protected static List<String> stdColumns = new ArrayList<String>();
    protected static List<String> allColumns = new ArrayList<String>();
    protected static String tableName = "Practitioner";

    static
    {
        pkColumns.add("id");
        stdColumns.add("SourceSystemId");
        stdColumns.add("Name");
        stdColumns.add("FirstName");
        stdColumns.add("LastName");
        stdColumns.add("Gender");
        stdColumns.add("Age");
        stdColumns.add("Street");
        stdColumns.add("City");
        stdColumns.add("State");
        stdColumns.add("PostalCode");
        stdColumns.add("Country");
        stdColumns.add("SpecalityCode");
        stdColumns.add("Specality");
        stdColumns.add("SubSpecality");
        stdColumns.add("ClinicalIntrest");
        stdColumns.add("Experience");
        stdColumns.add("PhotoURL");
        stdColumns.add("SourceURL");
        stdColumns.add("CreateTs");
        stdColumns.add("SubSpecality1");
        stdColumns.add("SubSpecality1Code");
        stdColumns.add("SubSpecality2");
        stdColumns.add("SubSpecality2Code");
        stdColumns.add("SubSpecality3");
        stdColumns.add("SubSpecality3Code");
        allColumns.addAll(pkColumns);
        allColumns.addAll(stdColumns);
    }

    @Autowired
    private DataSource mockDataSource;

    public List<Practitioner> selectAll() throws DAOException
    {
        List<Practitioner> ret = new ArrayList<Practitioner>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;

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

    protected Practitioner fromResultSet(ResultSet rs) throws SQLException
    {
        Practitioner obj = new Practitioner();

        obj.setId(DBUtil.getInt(rs, "id"));
        obj.setSourcesystemid(DBUtil.getString(rs, "SourceSystemId"));
        obj.setName(DBUtil.getString(rs, "Name"));
        obj.setFirstname(DBUtil.getString(rs, "FirstName"));
        obj.setLastname(DBUtil.getString(rs, "LastName"));
        obj.setGender(DBUtil.getString(rs, "Gender"));
        obj.setAge(DBUtil.getInteger(rs, "Age"));
        obj.setStreet(DBUtil.getString(rs, "Street"));
        obj.setCity(DBUtil.getString(rs, "City"));
        obj.setState(DBUtil.getString(rs, "State"));
        obj.setPostalcode(DBUtil.getString(rs, "PostalCode"));
        obj.setCountry(DBUtil.getString(rs, "Country"));
        obj.setSpecalitycode(DBUtil.getString(rs, "SpecalityCode"));
        obj.setSpecality(DBUtil.getString(rs, "Specality"));
        obj.setSubspecality(DBUtil.getString(rs, "SubSpecality"));
        obj.setClinicalintrest(DBUtil.getString(rs, "ClinicalIntrest"));
        obj.setExperience(DBUtil.getString(rs, "Experience"));
        obj.setPhotourl(DBUtil.getString(rs, "PhotoURL"));
        obj.setSourceurl(DBUtil.getString(rs, "SourceURL"));
        obj.setCreatets(DBUtil.getString(rs, "CreateTs"));
        obj.setSubSpecality1(DBUtil.getString(rs, "SubSpecality1"));
        obj.setSubSpecalityCode1(DBUtil.getString(rs, "SubSpecality1Code"));
        obj.setSubSpecality2(DBUtil.getString(rs, "SubSpecality2"));
        obj.setSubSpecalityCode2(DBUtil.getString(rs, "SubSpecality2Code"));
        obj.setSubSpecality3(DBUtil.getString(rs, "SubSpecality3"));
        obj.setSubSpecalityCode3(DBUtil.getString(rs, "SubSpecality3Code"));

        return obj;
    }

}
