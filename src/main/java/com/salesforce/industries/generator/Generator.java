package com.salesforce.industries.generator;

import java.io.IOException;
import java.util.List;

import com.salesforce.industries.exception.DAOException;

public interface Generator<T> {
    public List<T> generate(int recordCount,String recordTypeId,String nameSpace,String parentId) throws IOException, DAOException;

}
