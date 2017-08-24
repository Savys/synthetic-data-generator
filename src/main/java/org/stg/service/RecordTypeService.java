package org.stg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.stg.bootstrap.Bootstrap;
import org.stg.connection.PartnerConnectionPool;
import org.stg.core.IService;
import org.stg.pojo.RecordType;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.sobject.SObject;

@Service
public class RecordTypeService implements IService  {

    final static Logger logger = Logger.getLogger(RecordTypeService.class);

    protected static LoadingCache<String[], List<RecordType>> cache;

    public RecordTypeService() {
        buildCache();
    }

    private void buildCache() {
        cache = CacheBuilder.newBuilder().expireAfterWrite(100, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build(new CacheLoader<String[], List<RecordType>>() {
                    @Override
                    public List<RecordType> load(String[] sObjectTypes) throws Exception {
                        return getSFDCRecordTypes(sObjectTypes);
                    }
                });
    }

    public List<RecordType> getRecordTypes(String[] sObjectTypes) throws Exception {
        return cache.get(sObjectTypes);
    }

    private List<RecordType> getSFDCRecordTypes(String[] sObjectTypes) throws Exception {
        List<RecordType> rtList = new ArrayList<RecordType>();
        StringBuilder sb= new StringBuilder();
        String filter = "";
        for(int i=0;i<sObjectTypes.length;i++){
            sb.append( "'"+sObjectTypes[i]+"'," );
        }
        filter = sb.toString();
        filter = filter.substring(0, filter.length()-1);

        String soql="select DeveloperName, Id, IsActive, Name, NamespacePrefix, SobjectType from RecordType where SobjectType in ("+ filter + ")";

        PartnerConnectionPool pcPool=Bootstrap.getPartnerConnectionPool();
        PartnerConnection pc = pcPool.borrowObject();

        try {
            QueryResult qr = pc.query(soql);
            SObject[] sobjects = qr.getRecords();
            for(int i=0;i<sobjects.length;i++) {
                SObject sobject = sobjects[i];

                RecordType rt = new RecordType();
                rt.setDeveloperName((String)sobject.getSObjectField("DeveloperName"));
                String isActive = (String) sobject.getSObjectField("IsActive");
                if(isActive.equalsIgnoreCase("true")) {
                    rt.setIsActive(true);
                }else {
                    rt.setIsActive(false);
                }
                rt.setRecordTypeId((String)sobject.getSObjectField("Id"));
                rt.setNamespacePrefix((String)sobject.getSObjectField("NamespacePrefix"));
                rt.setsObjectType((String)sobject.getSObjectField("SobjectType"));
                rt.setRecordTypeName((String)sobject.getSObjectField("Name"));
                rtList.add(rt);
            }
        }finally {
            pcPool.returnObject(pc);
        }


        return rtList;
    }

}
