package org.stg.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.soap.metadata.ReadResult;
import com.sforce.soap.metadata.Metadata;
import com.sforce.ws.ConnectionException;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stg.bootstrap.Bootstrap;
import org.stg.connection.MetadataConnectionPool;
import org.stg.core.IService;

@Service
public class RecordTypeMetadataService implements IService {

    final static Logger logger = Logger.getLogger(RecordTypeMetadataService.class);
    @Autowired
    OrgService orgService;


    protected static LoadingCache<String, Metadata[]> cache;

    public RecordTypeMetadataService() {
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
                            ReadResult readResult = connection.readMetadata("RecordType", new String[] {objectName });
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

