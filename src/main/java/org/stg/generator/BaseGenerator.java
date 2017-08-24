package org.stg.generator;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.stg.core.MockDataSetHolder;

public abstract class BaseGenerator<T> implements Generator<T>{

    final static Logger logger = Logger.getLogger(BaseGenerator.class);

    @Autowired
    protected MockDataSetHolder mockDataSet;


}
