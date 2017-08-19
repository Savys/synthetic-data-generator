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
import com.salesforce.industries.persistence.AddressDAO;
import com.salesforce.industries.persistence.model.Address;

@Repository
public class AddressDAOImpl implements AddressDAO {

    @Autowired
    private DataSource mockDataSource;

    //
    // static data
    //
    protected static List<String> pkColumns = new ArrayList<String>();
    protected static List<String> stdColumns = new ArrayList<String>();
    protected static List<String> allColumns = new ArrayList<String>();
    protected static String tableName = "Address";

    static
    {
        pkColumns.add("id");
        stdColumns.add("HouseNumberStart");
        stdColumns.add("HouseNumberEnd");
        stdColumns.add("Street");
        stdColumns.add("Unit");
        stdColumns.add("City");
        stdColumns.add("PostalCode");
        stdColumns.add("State");
        stdColumns.add("Country");
        allColumns.addAll(pkColumns);
        allColumns.addAll(stdColumns);
    }

    public AddressDAOImpl()
    {
    }



    public List<Address> selectAll() throws DAOException
    {
        List<Address> ret = new ArrayList<Address>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try
        {
            connection = mockDataSource.getConnection();
            String sql = DBUtil.select(tableName, allColumns) + " limit  100000";
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

    protected Address fromResultSet(ResultSet rs) throws SQLException
    {
        Address obj = new Address();

        obj.setId(DBUtil.getInt(rs, "id"));
        obj.setHousenumberstart(DBUtil.getInteger(rs, "HouseNumberStart"));
        obj.setHousenumberend(DBUtil.getInteger(rs, "HouseNumberEnd"));
        obj.setStreet(DBUtil.getString(rs, "Street"));
        obj.setUnit(DBUtil.getString(rs, "Unit"));
        obj.setCity(DBUtil.getString(rs, "City"));
        obj.setPostalcode(DBUtil.getString(rs, "PostalCode"));
        obj.setState(DBUtil.getString(rs, "State"));
        obj.setCountry(DBUtil.getString(rs, "Country"));

        return obj;
    }

}
