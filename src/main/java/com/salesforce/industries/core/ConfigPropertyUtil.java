package com.salesforce.industries.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.salesforce.industries.core.Consts;
import com.salesforce.industries.pojo.LoginProperty;
import com.salesforce.industries.pojo.MockDataSourceProperty;

public class ConfigPropertyUtil {

    private LoadingCache<String, String> cache;

    private static ConfigPropertyUtil gt = new ConfigPropertyUtil();
    public static ConfigPropertyUtil getInstance(){ return gt; }

    private ConfigPropertyUtil() {
        cache = CacheBuilder.newBuilder()
                .refreshAfterWrite(20, TimeUnit.MINUTES)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        if(cache.size() < 1)  {
                            cache.putAll(getNewCacheContents());
                        }
                        return cache.get(key);
                    }

                });
    }

    private Map<String, String> getNewCacheContents() {
        return getTheData();
    }

    private String getEntry(String args) throws ExecutionException{
        return cache.get(args);
    }

    private Map<String, String> getTheData() {
        Map<String, String> entries = new  HashMap<String, String>();
        InputStream inputStream = null;

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
                Enumeration<?> e = prop.propertyNames();
                while (e.hasMoreElements()) {
                    String key = (String) e.nextElement();
                    entries.put(key, prop.getProperty(key));
                }

            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        }catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return entries;
    }

    public LoginProperty getOrgLoginProperty(String key) throws IOException, ExecutionException {
        LoginProperty loginProperty = new LoginProperty();
        loginProperty.setUserName(getEntry(Consts.PROP_USERNAME));
        loginProperty.setPassword(getEntry(Consts.PROP_PASSWORD));
        if(key.equalsIgnoreCase(Consts.METADATA_CONNECTION)) {
            loginProperty.setLoginEndPoint(getEntry(Consts.PROP_LOGINENDPOINT)+"/services/Soap/m/40.0");
        }else {
            loginProperty.setLoginEndPoint(getEntry(Consts.PROP_LOGINENDPOINT)+"/services/Soap/u/40.0");
        }

        //loginProperty.setLoginEndPoint(getEntry(Consts.PROP_LOGINENDPOINT));
        if(key.equalsIgnoreCase(Consts.METADATA_CONNECTION)) {
            loginProperty.setEndPoint(getEntry(Consts.PROP_ENDPOINT)+"/services/Soap/m/40.0");
        }else {
            loginProperty.setEndPoint(getEntry(Consts.PROP_ENDPOINT)+"/services/Soap/u/40.0");
        }

        return loginProperty;
    }

    public MockDataSourceProperty getMockDataSourceLoginProperty() throws IOException, ExecutionException {
        MockDataSourceProperty mockDataSource = new MockDataSourceProperty();
        mockDataSource.setDriver(getEntry(Consts.DB_DRIVER));
        mockDataSource.setDbHost(getEntry(Consts.DB_HOST));
        mockDataSource.setDbName(getEntry(Consts.DB_NAME));
        mockDataSource.setDbPassword(getEntry(Consts.DB_PASSWORD));
        mockDataSource.setDbUserName(getEntry(Consts.DB_USERNAME));

        return mockDataSource;
    }


    public static void main(String[] args) {
        ConfigPropertyUtil gt = ConfigPropertyUtil.getInstance();
        try {
            System.out.println("driver:"+gt.getEntry("driver"));
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
