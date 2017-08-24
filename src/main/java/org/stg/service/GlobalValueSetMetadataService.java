package org.stg.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.soap.metadata.ReadResult;
import com.sforce.soap.metadata.CustomValue;
import com.sforce.soap.metadata.GlobalValueSet;
import com.sforce.soap.metadata.Metadata;
import com.sforce.ws.ConnectionException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stg.bootstrap.Bootstrap;
import org.stg.connection.MetadataConnectionPool;
import org.stg.core.IService;

@Service
public class GlobalValueSetMetadataService implements IService {

    final static Logger logger = Logger.getLogger(GlobalValueSetMetadataService.class);
    @Autowired
    OrgService orgService;


    protected static LoadingCache<String, Metadata[]> cache;

    public GlobalValueSetMetadataService() {
        buildCache();
    }

    private void buildCache() {
        cache = CacheBuilder.newBuilder().expireAfterWrite(100, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build(new CacheLoader<String, Metadata[]>() {
                    @Override
                    public Metadata[] load(String objectName) throws ConnectionException,Exception {
                        MetadataConnectionPool mdPool=Bootstrap.getMetadataConnectionPool();
                        MetadataConnection connection = mdPool.borrowObject();

                        try {
                            ReadResult readResult = connection.readMetadata("GlobalValueSet", new String[] {objectName });
                            return readResult.getRecords();
                        }finally {
                            mdPool.returnObject(connection);
                        }
                    }
                });
    }

    public Metadata[] getObjectMetadata(String objectName) throws Exception {
        return cache.get(objectName);
    }

    public List<String> getPickListValues(String objectName, String fieldName) throws Exception {
        String nameSpace = orgService.getIndustriesNamespace();
        String fqFieldName = fieldName;
        if(fieldName.toLowerCase().endsWith("__c") && !fieldName.toLowerCase().contains(nameSpace.toLowerCase()+"__")) {
            fqFieldName = nameSpace+"__"+fqFieldName;
        }
        List<String> pickListValue = new ArrayList<String>();
        Metadata[] mdInfo = getObjectMetadata(objectName);
        for (Metadata md : mdInfo) {
            if (md != null) {
                GlobalValueSet obj = (GlobalValueSet) md;
                CustomValue[] customValues = obj.getCustomValue();
                for(CustomValue value : customValues ) {
                    pickListValue.add(value.getFullName());
                }
            } else {
                logger.debug("Empty metadata.");
            }
        }
        return pickListValue;
    }


}

