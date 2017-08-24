package org.stg.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.soap.metadata.ReadResult;
import com.sforce.soap.metadata.ValueSet;
import com.sforce.soap.metadata.ValueSetValuesDefinition;
import com.sforce.soap.metadata.CustomField;
import com.sforce.soap.metadata.CustomObject;
import com.sforce.soap.metadata.CustomValue;
import com.sforce.soap.metadata.FieldType;
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
import org.stg.core.RandUtil;

@Service
public class CustomObjectMetadataService implements IService {

    final static Logger logger = Logger.getLogger(CustomObjectMetadataService.class);
    @Autowired
    OrgService orgService;
    @Autowired
    GlobalValueSetMetadataService globalValueSetMetadataService;


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

    public List<String> getPickListValues(String objectName, String fieldName) throws Exception {
        String nameSpace = orgService.getIndustriesNamespace();
        String fqFieldName = fieldName;
        if(fieldName.toLowerCase().endsWith("__c")) {
            fqFieldName = nameSpace+"__"+fqFieldName;
        }
        List<String> pickListValue = new ArrayList<String>();
        Metadata[] mdInfo = getObjectMetadata(objectName);
        for (Metadata md : mdInfo) {
            if (md != null) {
                CustomObject obj = (CustomObject) md;
                CustomField[] fields = obj.getFields();
                for (CustomField field : fields) {
                    FieldType fieldType = field.getType();
                    if(field.getFullName().equalsIgnoreCase(fqFieldName)) {
                        if( fieldType.name().equalsIgnoreCase("Picklist") || fieldType.name().equalsIgnoreCase("MultiselectPicklist")) {
                            ValueSet valueSet = field.getValueSet();
                            ValueSetValuesDefinition vsd = valueSet.getValueSetDefinition();
                            if(vsd == null) {
                                pickListValue.addAll(globalValueSetMetadataService.getPickListValues(valueSet.getValueSetName(), fqFieldName));
                            }else {
                                CustomValue[] values = vsd.getValue();
                                for(int i=0;i<values.length;i++) {
                                    pickListValue.add(values[i].getFullName());
                                }
                            }
                            break;
                        }
                    }
                }
            } else {
                logger.debug("Empty metadata.");
            }
        }
        return pickListValue;
    }

    public String getRandomPickListValue(String objectName,String fieldName) {
        String returnValue = "";
        try {
            List<String> pickListValues = getPickListValues(objectName,fieldName);
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

