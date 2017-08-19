package com.salesforce.industries.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.salesforce.industries.bootstrap.Bootstrap;
import com.salesforce.industries.connection.MetadataConnectionPool;
import com.salesforce.industries.core.IService;
import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.soap.metadata.ReadResult;
import com.sforce.soap.metadata.Metadata;
import com.sforce.ws.ConnectionException;


import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CustomObjectMetadataService implements IService {

    final static Logger logger = Logger.getLogger(CustomObjectMetadataService.class);

    protected static LoadingCache<String, Metadata[]> cache;

    public CustomObjectMetadataService() {
        buildCache();
    }

    private void buildCache() {
        cache = CacheBuilder.newBuilder().expireAfterWrite(100, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build(new CacheLoader<String, Metadata[]>() {
                    @Override
                    public Metadata[] load(String objectName) throws ConnectionException,Exception {
                        logger.debug("CustomObjectMetadataService: Getting from Backend");

                        MetadataConnectionPool mdPool=Bootstrap.getMetadataConnectionPool();
                        MetadataConnection connection = mdPool.borrowObject();

                        try {
                            ReadResult readResult = connection.readMetadata("CustomObject", new String[] {objectName });
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

}

