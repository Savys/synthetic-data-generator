package com.salesforce.industries.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.salesforce.industries.core.IService;
import com.sforce.soap.metadata.CustomField;
import com.sforce.soap.metadata.CustomObject;
import com.sforce.soap.metadata.Metadata;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrgService implements IService {
    final static Logger logger = Logger.getLogger(OrgService.class);

    @Autowired
    CustomObjectMetadataService customObjectMetadataService;

    public static final String NAMESPACE = "INDUSTRIES_NAMESPACE";

    protected static LoadingCache<String, String> cache;

    public OrgService() {
        buildCache();
    }

    private void buildCache() {
        cache = CacheBuilder.newBuilder().expireAfterWrite(100, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String paramName) throws Exception {
                        String value = "";
                        if(paramName.equals(NAMESPACE)) {
                            value = dedectIndustriesNS();
                        }
                        return value;
                    }
                });
    }

    public String getIndustriesNamespace() throws Exception {
        return cache.get(NAMESPACE);
    }

    private String dedectIndustriesNS() throws Exception {
        logger.debug("dedectIndustriesNS: Getting from Backend");
        String nameSpae="";
        try {
            Metadata[] mdInfo = customObjectMetadataService.getObjectMetadata("Account");
            for (Metadata md : mdInfo) {
                if (md != null) {
                    CustomObject obj = (CustomObject) md;
                    for(CustomField field: obj.getFields()) {
                        if(field.getFullName().contains("PrimaryContact__c")) {
                            String[] result = field.getFullName().split("__");
                            if(result.length > 0) {
                                nameSpae = result[0];
                                logger.debug("NS:"+result[0]);
                            }
                        }
                    }
                } else {
                    logger.debug("Empty metadata.");
                }
            }

        }catch(Exception ex) {
            logger.error("Exception Occured:"+ex.getMessage());
            throw ex;
        }
        return nameSpae;
    }



}
