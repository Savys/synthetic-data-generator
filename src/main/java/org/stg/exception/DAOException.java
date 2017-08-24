package org.stg.exception;

public class DAOException extends Exception
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DAOException(String s, Exception e)
    {
        super(s, e);
    }

    public DAOException(String e)
    {
        super(e);
    }

    public DAOException(Exception e)
    {
        super(e);
    }
}