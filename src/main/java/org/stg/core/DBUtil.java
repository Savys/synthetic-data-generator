package org.stg.core;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBUtil
{

    //
    // helpers for closing, etc
    //
    public static void close(ResultSet res)
    {
        close(null,null, res);
    }

    public static void close(Connection connection,Statement stmt, ResultSet res){

        try
        {
            if (res != null)
            {
                res.close();
            }
        }
        catch (SQLException ex)
        {
        }
        finally
        {
            try
            {
                if (stmt != null)
                {
                    stmt.close();
                }
            }
            catch (SQLException ex)
            {
            }finally {
                if(connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    protected static String getQuestionMarks(int size)
    {
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < size; i++)
        {
            if (i > 0)
            {
                buf.append(", ");
            }

            buf.append("?");
        }

        return buf.toString();
    }

    public static String select(String tableName, List<String> selectColumns)
    {
        return select(tableName, selectColumns, new ArrayList<String>());
    }

    public static String select(String tableName, List<String> selectColumns, List<String> whereColumns)
    {
        StringBuffer buf = new StringBuffer("SELECT ");

        for (int i = 0; i < selectColumns.size(); i++)
        {
            if (i > 0)
            {
                buf.append(", ");
            }

            buf.append(selectColumns.get(i));
        }

        buf.append(" FROM ").append(tableName);

        if (whereColumns.size() > 0)
        {
            buf.append(" WHERE ");

            for (int i = 0; i < whereColumns.size(); i++)
            {
                if (i > 0)
                {
                    buf.append(" AND ");
                }

                buf.append(whereColumns.get(i)).append(" = ?");
            }
        }

        return buf.toString();
    }

    public static String selectNull(String tableName, List<String> selectColumns, List<String> whereColumns)
    {
        StringBuffer buf = new StringBuffer("SELECT ");

        for (int i = 0; i < selectColumns.size(); i++)
        {
            if (i > 0)
            {
                buf.append(", ");
            }

            buf.append(selectColumns.get(i));
        }

        buf.append(" FROM ").append(tableName);

        if (whereColumns.size() > 0)
        {
            buf.append(" WHERE ");

            for (int i = 0; i < whereColumns.size(); i++)
            {
                if (i > 0)
                {
                    buf.append(" AND ");
                }

                buf.append(whereColumns.get(i)).append(" IS NULL");
            }
        }

        return buf.toString();
    }

    public static String getString(ResultSet rs, String col)
            throws SQLException
    {
        return rs.getString(col);
    }

    public static int getInt(ResultSet rs, String col) throws SQLException
    {
        return rs.getInt(col);
    }

    public static boolean getBoolean(ResultSet rs, String col)
            throws SQLException
    {
        return rs.getBoolean(col);
    }

    public static Boolean getBooleanObject(ResultSet rs, String col)
            throws SQLException
    {
        return rs.getBoolean(col) ? Boolean.TRUE : Boolean.FALSE;
    }

    public static long getLong(ResultSet rs, String col)
            throws SQLException
    {
        return rs.getLong(col);
    }

    public static double getDouble(ResultSet rs, String col)
            throws SQLException
    {
        return rs.getDouble(col);
    }

    public static byte getByte(ResultSet rs, String col)
            throws SQLException
    {
        return rs.getByte(col);
    }

    public static short getShort(ResultSet rs, String col)
            throws SQLException
    {
        return rs.getShort(col);
    }

    public static Integer getInteger(ResultSet rs, String col)
            throws SQLException
    {
        int val = rs.getInt(col);

        return rs.wasNull() ? null : new Integer(val);
    }

    public static Double getDoubleObject(ResultSet rs, String col)
            throws SQLException
    {
        double val = rs.getDouble(col);

        return rs.wasNull() ? null : new Double(val);
    }

    public static Byte getByteObject(ResultSet rs, String col)
            throws SQLException
    {
        byte val = rs.getByte(col);

        return rs.wasNull() ? null : new Byte(val);
    }

    public static Short getShortObject(ResultSet rs, String col)
            throws SQLException
    {
        short val = rs.getShort(col);

        return rs.wasNull() ? null : new Short(val);
    }

    public static Long getLongObject(ResultSet rs, String col)
            throws SQLException
    {
        long val = rs.getLong(col);

        return rs.wasNull() ? null : new Long(val);
    }

    public static BigDecimal getBigDecimal(ResultSet rs, String col)
            throws SQLException
    {
        return rs.getBigDecimal(col);
    }

    public static byte[] getByteArray(ResultSet rs, String col)
            throws SQLException
    {
        return rs.getBytes(col);
    }

    public static Date getTimestamp(ResultSet rs, String col)
            throws SQLException
    {
        return rs.getTimestamp(col);
    }

    public static Date getDate(ResultSet rs, String col)
            throws SQLException
    {
        return rs.getTimestamp(col);
    }

    public static String getString(ResultSet rs, int col)
            throws SQLException
    {
        return rs.getString(col);
    }

    public static Clob getClob(ResultSet rs, String col)
            throws SQLException
    {
        return rs.getClob(col);
    }

    public static Blob getBlob(ResultSet rs, String col)
            throws SQLException
    {
        return rs.getBlob(col);
    }

    public static int getInt(ResultSet rs, int col) throws SQLException
    {
        return rs.getInt(col);
    }

    public static boolean getBoolean(ResultSet rs, int col)
            throws SQLException
    {
        return rs.getBoolean(col);
    }

    public static Boolean getBooleanObject(ResultSet rs, int col)
            throws SQLException
    {
        return rs.getBoolean(col) ? Boolean.TRUE : Boolean.FALSE;
    }

    public static long getLong(ResultSet rs, int col) throws SQLException
    {
        return rs.getLong(col);
    }

    public static double getDouble(ResultSet rs, int col)
            throws SQLException
    {
        return rs.getDouble(col);
    }

    public static byte getByte(ResultSet rs, int col) throws SQLException
    {
        return rs.getByte(col);
    }

    public static short getShort(ResultSet rs, int col) throws SQLException
    {
        return rs.getShort(col);
    }

    public static Integer getInteger(ResultSet rs, int col)
            throws SQLException
    {
        int val = rs.getInt(col);

        return rs.wasNull() ? null : new Integer(val);
    }

    public static Double getDoubleObject(ResultSet rs, int col)
            throws SQLException
    {
        double val = rs.getDouble(col);

        return rs.wasNull() ? null : new Double(val);
    }

    public static Byte getByteObject(ResultSet rs, int col)
            throws SQLException
    {
        byte val = rs.getByte(col);

        return rs.wasNull() ? null : new Byte(val);
    }

    public static Short getShortObject(ResultSet rs, int col)
            throws SQLException
    {
        short val = rs.getShort(col);

        return rs.wasNull() ? null : new Short(val);
    }

    public static Long getLongObject(ResultSet rs, int col)
            throws SQLException
    {
        long val = rs.getLong(col);

        return rs.wasNull() ? null : new Long(val);
    }

    public static BigDecimal getBigDecimal(ResultSet rs, int col)
            throws SQLException
    {
        return rs.getBigDecimal(col);
    }

    public static byte[] getByteArray(ResultSet rs, int col)
            throws SQLException
    {
        return rs.getBytes(col);
    }

    public static Date getTimestamp(ResultSet rs, int col)
            throws SQLException
    {
        return rs.getTimestamp(col);
    }

    public static Date getDate(ResultSet rs, int col) throws SQLException
    {
        return rs.getTimestamp(col);
    }

    //
    // Bind methods
    //
    public static void bind(PreparedStatement ps, int pos, Object val)
            throws SQLException
    {
        if (null == val)
        {
            ps.setNull(pos, Types.VARCHAR);
        }
        else
        {
            ps.setObject(pos, val);
        }
    }

    public static void bind(PreparedStatement ps, int pos, Enum<?> val)
            throws SQLException
    {
        if (null == val)
        {
            ps.setNull(pos, Types.INTEGER);
        }
        else
        {
            ps.setInt(pos, val.ordinal());
        }
    }

    public static void bind(PreparedStatement ps, int pos, Blob val)
            throws SQLException
    {
        if (null == val)
        {
            ps.setNull(pos, Types.BLOB);
        }
        else
        {
            ps.setBlob(pos, val);
        }
    }

    public static void bind(PreparedStatement ps, int pos, Clob val)
            throws SQLException
    {
        if (null == val)
        {
            ps.setNull(pos, Types.CLOB);
        }
        else
        {
            ps.setClob(pos, val);
        }
    }

    public static void bind(PreparedStatement ps, int pos, boolean val)
            throws SQLException
    {
        bind(ps, pos, val ? Boolean.TRUE : Boolean.FALSE);
    }

    public static void bind(PreparedStatement ps, int pos, Boolean val)
            throws SQLException
    {
        if (null == val)
        {
            ps.setNull(pos, Types.BOOLEAN);
        }
        else
        {
            ps.setBoolean(pos, val.booleanValue());
        }
    }

    public static void bind(PreparedStatement ps, int pos, int val)
            throws SQLException
    {
        ps.setInt(pos, val);
    }

    public static void bind(PreparedStatement ps, int pos, Integer val)
            throws SQLException
    {
        if (null == val)
        {
            ps.setNull(pos, Types.INTEGER);
        }
        else
        {
            ps.setInt(pos, val.intValue());
        }
    }

    public static void bind(PreparedStatement ps, int pos, long val)
            throws SQLException
    {
        ps.setLong(pos, val);
    }

    public static void bind(PreparedStatement ps, int pos, Long val)
            throws SQLException
    {
        if (null == val)
        {
            ps.setNull(pos, Types.INTEGER);
        }
        else
        {
            ps.setLong(pos, val.longValue());
        }
    }

    public static void bind(PreparedStatement ps, int pos, double val)
            throws SQLException
    {
        ps.setDouble(pos, val);
    }

    public static void bind(PreparedStatement ps, int pos, Double val)
            throws SQLException
    {
        if (null == val)
        {
            ps.setNull(pos, Types.DOUBLE);
        }
        else
        {
            ps.setDouble(pos, val.doubleValue());
        }
    }

    public static void bind(PreparedStatement ps, int pos, byte val)
            throws SQLException
    {
        ps.setByte(pos, val);
    }

    public static void bind(PreparedStatement ps, int pos, Byte val)
            throws SQLException
    {
        if (null == val)
        {
            ps.setNull(pos, Types.BINARY);
        }
        else
        {
            ps.setByte(pos, val.byteValue());
        }
    }

    public static void bind(PreparedStatement ps, int pos, short val)
            throws SQLException
    {
        ps.setShort(pos, val);
    }

    public static void bind(PreparedStatement ps, int pos, Short val)
            throws SQLException
    {
        if (null == val)
        {
            ps.setNull(pos, Types.SMALLINT);
        }
        else
        {
            ps.setShort(pos, val.shortValue());
        }
    }

    public static void bind(PreparedStatement ps, int pos, String val)
            throws SQLException
    {
        if (null == val)
        {
            ps.setNull(pos, Types.VARCHAR);
        }
        else
        {
            ps.setString(pos, val);
        }
    }

    public static void bind(PreparedStatement ps, int pos, Date val)
            throws SQLException
    {
        if (null == val)
        {
            ps.setNull(pos, Types.TIMESTAMP);
        }
        else
        {
            ps.setTimestamp(pos, new java.sql.Timestamp(val.getTime()));
        }
    }

    public static void bind(PreparedStatement ps, int pos, BigDecimal val)
            throws SQLException
    {
        if (null == val)
        {
            ps.setNull(pos, Types.DECIMAL);
        }
        else
        {
            ps.setBigDecimal(pos, val);
        }
    }

    public static void bind(PreparedStatement ps, int pos, BigInteger val)
            throws SQLException
    {
        if (null == val)
        {
            ps.setNull(pos, Types.BIGINT);
        }
        else
        {
            ps.setBigDecimal(pos, new BigDecimal(val));
        }
    }

    public static void bind(PreparedStatement ps, int pos, byte[] val)
            throws SQLException
    {
        if (null == val)
        {
            ps.setNull(pos, Types.VARBINARY);
        }
        else
        {
            ps.setBytes(pos, val);
        }
    }
}