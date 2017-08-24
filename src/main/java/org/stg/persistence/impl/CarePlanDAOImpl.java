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
import org.stg.persistence.CarePlanDAO;
import org.stg.persistence.model.CarePlan;

@Repository
public class CarePlanDAOImpl implements CarePlanDAO {

    @Autowired
    private DataSource mockDataSource;

    //
    // static data
    //
    protected static List<String> pkColumns = new ArrayList<String>();
    protected static List<String> stdColumns = new ArrayList<String>();
    protected static List<String> allColumns = new ArrayList<String>();
    protected static String tableName = "CarePlans";

    static
    {
        pkColumns.add("id");
        stdColumns.add("Name");
        allColumns.addAll(pkColumns);
        allColumns.addAll(stdColumns);
    }

    public CarePlanDAOImpl()
    {
    }



    public List<CarePlan> selectAll() throws DAOException
    {
        List<CarePlan> ret = new ArrayList<CarePlan>();
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

    protected CarePlan fromResultSet(ResultSet rs) throws SQLException
    {
        CarePlan obj = new CarePlan();
        obj.setId(DBUtil.getInteger(rs, "Id"));
        obj.setSubject(DBUtil.getString(rs, "Name"));
        return obj;
    }

}
