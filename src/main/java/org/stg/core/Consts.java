package org.stg.core;

import java.text.SimpleDateFormat;

public class Consts {

    /** Opposite of {@link #FAILS}.  */
    public static final boolean PASSES = true;
    /** Opposite of {@link #PASSES}.  */
    public static final boolean FAILS = false;

    /** Opposite of {@link #FAILURE}.  */
    public static final boolean SUCCESS = true;
    /** Opposite of {@link #SUCCESS}.  */
    public static final boolean FAILURE = false;

    /**
	   Useful for {@link String} operations, which return an index of <tt>-1</tt> when
	   an item is not found.
     */
    public static final int NOT_FOUND = -1;

    /** System property - <tt>line.separator</tt>*/
    public static final String NEW_LINE = System.getProperty("line.separator");
    /** System property - <tt>file.separator</tt>*/
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    /** System property - <tt>path.separator</tt>*/
    public static final String PATH_SEPARATOR = System.getProperty("path.separator");

    public static final String EMPTY_STRING = "";
    public static final String SPACE = " ";
    public static final String TAB = "\t";
    public static final String SINGLE_QUOTE = "'";
    public static final String PERIOD = ".";
    public static final String DOUBLE_QUOTE = "\"";

    public static final String PROP_FILENAME = "config.properties";
    public static final String PROP_USERNAME = "userName";
    public static final String PROP_PASSWORD = "password";
    public static final String PROP_ENDPOINT = "endPoint";
    public static final String PROP_LOGINENDPOINT = "loginEndPoint";

    public static final String PARTNER_CONNECTION = "PARTNER";
    public static final String METADATA_CONNECTION = "METADATA";

    public static final String SOBJECT_ACCOUNT = "ACCOUNT";
    public static final String SOBJECT_CONTACT = "CONTACT";
    public static final String SOBJECT_LEAD = "LEAD";
    public static final String SOBJECT_CASE = "CASE";
    public static final String SOBJECT_CASETEAM = "CASETEAMMEMBER";
    public static final String SOBJECT_PROBLEM = "CarePlanProblem__c";
    public static final String SOBJECT_GOAL= "CarePlanGoal__c";
    public static final String SOBJECT_TASK = "Task";

    public static final String DB_HOST = "dbHost";
    public static final String DB_NAME = "dbName";
    public static final String DB_USERNAME = "dbUserName";
    public static final String DB_PASSWORD = "dbPassword";
    public static final String DB_DRIVER = "driver";

    public static final String RECORDTYPE_CATEGORY_INDIVIDUAL = "Individual";
    public static final String RECORDTYPE_CATEGORY_CAREPLAN = "CarePlan";
    public static final String SFDC_DATEFORMAT = "yyyy-MM-dd";
    public static SimpleDateFormat SFDC_DATEFMT = new SimpleDateFormat("yyyy-MM-dd");



    // PRIVATE //

    /**
	   The caller references the constants using <tt>Consts.EMPTY_STRING</tt>,
	   and so on. Thus, the caller should be prevented from constructing objects of
	   this class, by declaring this private constructor.
     */
    private Consts(){
        //this prevents even the native class from
        //calling this ctor as well :
        throw new AssertionError();
    }

}
