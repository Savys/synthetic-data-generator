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

import com.google.common.base.CaseFormat;
import com.salesforce.industries.core.DBUtil;
import com.salesforce.industries.exception.DAOException;
import com.salesforce.industries.persistence.LastNameDAO;
import com.salesforce.industries.persistence.model.LastName;

@Repository
public class LastNameDAOImpl implements LastNameDAO
{

    @Autowired
    private DataSource mockDataSource;

    //
    // static data
    //
    protected static List<String> pkColumns = new ArrayList<String>();
    protected static List<String> stdColumns = new ArrayList<String>();
    protected static List<String> allColumns = new ArrayList<String>();
    protected static String tableName = "LastNames";

    static
    {
        pkColumns.add("id");
        stdColumns.add("LastName");
        allColumns.addAll(pkColumns);
        allColumns.addAll(stdColumns);
    }

    public LastNameDAOImpl()	{

    }


    public List<LastName> selectAll() throws DAOException
    {
        List<LastName> ret = new ArrayList<LastName>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection=null;
        try
        {
            connection = mockDataSource.getConnection();
            ps =connection.prepareStatement(DBUtil.select(tableName, allColumns));
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

    protected LastName fromResultSet(ResultSet rs) throws SQLException
    {
        LastName obj = new LastName();

        obj.setId(DBUtil.getInt(rs, "id"));
        String result = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, DBUtil.getString(rs, "LastName"));
        obj.setLastname(result);

        return obj;
    }

}
