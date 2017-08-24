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
import org.stg.persistence.BusinessNameDAO;
import org.stg.persistence.model.BusinessName;

@Repository
public class BusinessNameDAOImpl implements BusinessNameDAO {

    @Autowired
    private DataSource mockDataSource;

    //
    // static data
    //
    protected static List<String> pkColumns = new ArrayList<String>();
    protected static List<String> stdColumns = new ArrayList<String>();
    protected static List<String> allColumns = new ArrayList<String>();
    protected static String tableName = "BusinessNames";

    static
    {
        pkColumns.add("id");
        stdColumns.add("Name");
        stdColumns.add("FortuneNumber");
        allColumns.addAll(pkColumns);
        allColumns.addAll(stdColumns);
    }

    public BusinessNameDAOImpl()
    {
    }



    public List<BusinessName> selectAll() throws DAOException
    {
        List<BusinessName> ret = new ArrayList<BusinessName>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try
        {
            connection = mockDataSource.getConnection();
            String sql = DBUtil.select(tableName, allColumns);
            ps = connection.prepareStatement(sql);
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

    @Override
    public List<BusinessName> selectFortune(int number) throws DAOException {
        List<BusinessName> ret = new ArrayList<BusinessName>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try
        {
            connection = mockDataSource.getConnection();
            String sql = "Select Name, FortuneNumber from " + tableName + " Where FortuneNumber < " + number;
            ps = connection.prepareStatement(sql);
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

    protected BusinessName fromResultSet(ResultSet rs) throws SQLException
    {
        BusinessName obj = new BusinessName();
        obj.setName(DBUtil.getString(rs, "Name"));
        obj.setFortuneNumber(DBUtil.getInteger(rs, "FortuneNumber")==null?0:DBUtil.getInteger(rs, "FortuneNumber"));
        return obj;
    }
}
