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
import org.stg.persistence.FirstNameDAO;
import org.stg.persistence.model.FirstName;

@Repository
public class FirstNameDAOImpl implements FirstNameDAO
{

    @Autowired
    private DataSource mockDataSource;

    //
    // static data
    //
    protected static List<String> pkColumns = new ArrayList<String>();
    protected static List<String>  stdColumns = new ArrayList<String> ();
    protected static List<String>  allColumns = new ArrayList<String> ();
    protected static String tableName = "FirstNames";

    static
    {
        pkColumns.add("id");
        stdColumns.add("Name");
        stdColumns.add("Gender");
        allColumns.addAll(pkColumns);
        allColumns.addAll(stdColumns);
    }

    public FirstNameDAOImpl()
    {
    }


    public List<FirstName> selectAll() throws DAOException
    {
        List<FirstName> ret = new ArrayList<FirstName>();
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


    protected FirstName fromResultSet(ResultSet rs) throws SQLException
    {
        FirstName obj = new FirstName();

        obj.setId(DBUtil.getInt(rs, "id"));
        obj.setName(DBUtil.getString(rs, "Name"));
        obj.setGender(DBUtil.getString(rs, "Gender"));

        return obj;
    }


}