package org.stg.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sforce.soap.partner.DescribeLayoutResult;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.PicklistEntry;
import com.sforce.soap.partner.PicklistForRecordType;
import com.sforce.soap.partner.RecordTypeMapping;
import com.sforce.ws.ConnectionException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stg.bootstrap.Bootstrap;
import org.stg.connection.PartnerConnectionPool;
import org.stg.core.IService;
import org.stg.core.RandUtil;

@Service
public class LayoutMetadataService implements IService {

    final static Logger logger = Logger.getLogger(LayoutMetadataService.class);
    @Autowired
    OrgService orgService;


    protected static LoadingCache<String, RecordTypeMapping[]> cache;

    public LayoutMetadataService() {
        buildCache();
    }

    private void buildCache() {
        cache = CacheBuilder.newBuilder().expireAfterWrite(100, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build(new CacheLoader<String, RecordTypeMapping[]>() {
                    @Override
                    public RecordTypeMapping[] load(String queryId) throws ConnectionException,Exception {
                        PartnerConnectionPool  pPool = Bootstrap.getPartnerConnectionPool();
                        PartnerConnection pConnection = pPool.borrowObject();
                        String[] str = queryId.split(":");
                        String object = str[0];
                        String recordTypeId = str[1];
                        try {
                            DescribeLayoutResult descResult = pConnection.describeLayout(object, null,  new String[] {recordTypeId });
                            return descResult.getRecordTypeMappings();
                        }finally {
                            pPool.returnObject(pConnection);
                        }
                    }
                });
    }

    public RecordTypeMapping[] getObjectMetadata(String objectName,String recordTypeId) throws Exception {
        return cache.get(objectName+":"+recordTypeId);
    }

    public List<String> getPickListValues(String objectName, String recordTypeId,String fieldName) throws Exception {
        String nameSpace = orgService.getIndustriesNamespace();
        String fqFieldName = fieldName;
        if(fieldName.toLowerCase().endsWith("__c")) {
            fqFieldName = nameSpace+"__"+fqFieldName;
        }
        List<String> pickListValues = new ArrayList<String>();
        RecordTypeMapping[] rtInfos = getObjectMetadata(objectName,recordTypeId);
        for(RecordTypeMapping rtInfo:rtInfos) {
            PicklistForRecordType[] pickLists = rtInfo.getPicklistsForRecordType();
            for(PicklistForRecordType pList : pickLists) {
                if(pList.getPicklistName().equalsIgnoreCase(fqFieldName)) {
                    PicklistEntry[] entries = pList.getPicklistValues();
                    for(PicklistEntry entry : entries) {
                        pickListValues.add(entry.getValue());
                    }
                }
            }
        }

        return pickListValues;
    }

    public String getRandomPickListValue(String objectName,String recordTypeId,String fieldName) {
        String returnValue = "";
        try {
            List<String> pickListValues = getPickListValues(objectName,recordTypeId,fieldName);
            if(pickListValues.size() > 0) {
                int pickListIndex = RandUtil.generateRandomInteger(0, pickListValues.size()-1);
                returnValue = pickListValues.get(pickListIndex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnValue;
    }
}

