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
import org.stg.persistence.OccupationDAO;
import org.stg.persistence.model.Occupation;

@Repository
public class OccupationDAOImpl implements OccupationDAO {

    @Autowired
    private DataSource mockDataSource;

    //
    // static data
    //
    protected static List<String> pkColumns = new ArrayList<String>();
    protected static List<String> stdColumns = new ArrayList<String>();
    protected static List<String> allColumns = new ArrayList<String>();
    protected static String tableName = "Occupation";

    static
    {
        pkColumns.add("id");
        stdColumns.add("SOCNumber");
        stdColumns.add("Name");
        stdColumns.add("AnnualSalary");
        allColumns.addAll(pkColumns);
        allColumns.addAll(stdColumns);
    }

    public OccupationDAOImpl()
    {
    }



    public List<Occupation> selectAll() throws DAOException
    {
        List<Occupation> ret = new ArrayList<Occupation>();
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
    public List<Occupation> selectWithSalary() throws DAOException {
        List<Occupation > ret = new ArrayList<Occupation>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try
        {
            connection = mockDataSource.getConnection();
            String sql = "Select Id,SOCNumber, Name,AnnualSalary from " + tableName + " Where AnnualSalary is not NULL";
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

    protected Occupation fromResultSet(ResultSet rs) throws SQLException
    {
        Occupation obj = new Occupation();
        obj.setId(DBUtil.getLongObject(rs, "Id"));
        obj.setSOCNumber(DBUtil.getString(rs, "SOCNumber"));
        obj.setName(DBUtil.getString(rs, "Name"));
        obj.setAnnualizedSalary(DBUtil.getDoubleObject(rs, "AnnualSalary"));
        return obj;
    }
}
