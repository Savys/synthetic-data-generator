package com.salesforce.industries.generator;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.salesforce.industries.core.MockDataSetHolder;
import com.salesforce.industries.exception.DAOException;

public abstract class BaseGenerator<T> implements Generator<T>{

    final static Logger logger = Logger.getLogger(BaseGenerator.class);

    @Autowired
    protected MockDataSetHolder mockDataSet;

    public BaseGenerator() throws DAOException {
        //mockDataSet = MockDataSetHolder.getInstance();
    }


}
