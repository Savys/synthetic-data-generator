package org.stg.generator;

import java.io.IOException;
import java.util.List;

import org.stg.exception.DAOException;

public interface Generator<T> {
    public List<T> generate(int recordCount,String recordTypeId,String nameSpace,String parentId) throws IOException, DAOException,Exception;

}
