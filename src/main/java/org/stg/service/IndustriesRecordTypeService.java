package org.stg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stg.bootstrap.Bootstrap;
import org.stg.connection.PartnerConnectionPool;
import org.stg.core.Consts;
import org.stg.core.IService;
import org.stg.pojo.IndustriesRecordType;
import org.stg.pojo.RecordType;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.sobject.SObject;

@Service
public class IndustriesRecordTypeService implements IService  {

    final static Logger logger = Logger.getLogger(IndustriesRecordTypeService.class);
    @Autowired
    OrgService orgService;
    @Autowired
    RecordTypeService rtService;

    protected static LoadingCache<String, List<IndustriesRecordType>> cache;

    public IndustriesRecordTypeService() {
        buildCache();
    }

    private void buildCache() {
        cache = CacheBuilder.newBuilder().expireAfterWrite(100, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build(new CacheLoader<String, List<IndustriesRecordType>>() {
                    @Override
                    public List<IndustriesRecordType> load(String paramName) throws Exception {
                        if(Consts.RECORDTYPE_CATEGORY_INDIVIDUAL.equalsIgnoreCase(paramName)) {
                            return getIndividualRecordTypes(paramName);
                        }else if (Consts.RECORDTYPE_CATEGORY_CAREPLAN.equalsIgnoreCase(paramName)) {
                            return getCarePlanRecordTypes(paramName);
                        }else {
                            return null;
                        }
                    }
                });
    }

    public List<IndustriesRecordType> getIndustriesRecordTypes(String paramName) throws Exception {
        return cache.get(paramName);
    }

    private List<IndustriesRecordType> getIndividualRecordTypes(String paramName) throws Exception {

        logger.debug("getIndividualRecordTypes: Getting from Backend");
        String nameSpace = orgService.getIndustriesNamespace();

        String[] sObjectTypes = {"Account", "Contact"};
        List<RecordType> rtList = rtService.getRecordTypes(sObjectTypes);

        String acctRecordTypeField = nameSpace+"__AccountRecordType__c";
        String contactRecordTypeField = nameSpace+"__ContactRecordType__c";
        String acctRecordTypeNSField = nameSpace+"__AccountRecordTypeNamespace__c";
        String contactRecordTypeNSField = nameSpace+"__ContactRecordTypeNamespace__c";
        String rtmObjectName = nameSpace+"__IndividualRecordTypeMapper__mdt";

        String soql="select " + acctRecordTypeField +", " + acctRecordTypeNSField + ", "+contactRecordTypeField +", " + contactRecordTypeNSField + "  from "+ rtmObjectName;
        List<IndustriesRecordType> iRtList = new ArrayList<IndustriesRecordType>();

        PartnerConnectionPool pcPool=Bootstrap.getPartnerConnectionPool();
        PartnerConnection pc = pcPool.borrowObject();


        try {
            QueryResult qr = pc.query(soql);
            SObject[] sobjects = qr.getRecords();
            for(int i=0;i<sobjects.length;i++) {
                SObject sobject = sobjects[i];

                String rtName = (String)sobject.getSObjectField(acctRecordTypeField);
                String rtNameSpace = (String)sobject.getSObjectField(acctRecordTypeNSField);
                if(rtNameSpace.equals("Industries")) {
                    rtNameSpace = nameSpace;
                }

                for(int j=0;j<rtList.size();j++) {
                    RecordType currentRT = rtList.get(j);
                    if(
                            (rtName.equalsIgnoreCase(currentRT.getRecordTypeName()) || rtName.equalsIgnoreCase(currentRT.getDeveloperName()))
                            && currentRT.getsObjectType().equalsIgnoreCase("Account")
                            && currentRT.getIsActive() && rtNameSpace.equalsIgnoreCase(currentRT.getNamespacePrefix())) {

                        IndustriesRecordType aRt = new IndustriesRecordType();
                        aRt.setRecordTypeId(currentRT.getRecordTypeId());
                        aRt.setsObjectType("Account");
                        aRt.setRecordTypeName(rtName);
                        aRt.setNameSpace(rtNameSpace);
                        iRtList.add(aRt);
                        break;
                    }
                }

                rtName = (String)sobject.getSObjectField(contactRecordTypeField);
                rtNameSpace = (String)sobject.getSObjectField(contactRecordTypeNSField);
                if(rtNameSpace.equals("Industries")) {
                    rtNameSpace = nameSpace;
                }
                for(int j=0;j<rtList.size();j++) {
                    RecordType currentRT = rtList.get(j);
                    if(
                            (rtName.equalsIgnoreCase(currentRT.getRecordTypeName()) || rtName.equalsIgnoreCase(currentRT.getDeveloperName()))
                            && currentRT.getsObjectType().equalsIgnoreCase("Contact")
                            && currentRT.getIsActive() && rtNameSpace.equalsIgnoreCase(currentRT.getNamespacePrefix())) {

                        IndustriesRecordType aRt = new IndustriesRecordType();
                        aRt.setRecordTypeId(currentRT.getRecordTypeId());
                        aRt.setsObjectType("Contact");
                        aRt.setRecordTypeName(rtName);
                        aRt.setNameSpace(rtNameSpace);
                        iRtList.add(aRt);
                        break;
                    }
                }
            }
        }finally {
            pcPool.returnObject(pc);
        }
        return iRtList;
    }

    private List<IndustriesRecordType> getCarePlanRecordTypes(String paramName) throws Exception {

        logger.debug("getCarePlanRecordTypes: Getting from Backend");
        String nameSpace = orgService.getIndustriesNamespace();

        String[] sObjectTypes = {"Case"};
        List<RecordType> rtList = rtService.getRecordTypes(sObjectTypes);

        String caseRecordTypeNameField = nameSpace+"__CaseRecordTypeName__c";
        String caseRecordTypeNSField = nameSpace+"__CaseRecordTypeNamespace__c";
        String activeFlagField = nameSpace+"__Active__c";

        String cpRTMObjectName = nameSpace+"__CarePlanRecordType__mdt";

        String soql="select " + caseRecordTypeNameField +", " + caseRecordTypeNSField + ", "+"NamespacePrefix" + "  from "+ cpRTMObjectName + " where " + activeFlagField + " = true";
        List<IndustriesRecordType> cRtList = new ArrayList<IndustriesRecordType>();

        PartnerConnectionPool pcPool=Bootstrap.getPartnerConnectionPool();
        PartnerConnection pc = pcPool.borrowObject();


        try {
            QueryResult qr = pc.query(soql);
            SObject[] sobjects = qr.getRecords();
            for(int i=0;i<sobjects.length;i++) {
                SObject sobject = sobjects[i];

                String rtName = (String)sobject.getSObjectField(caseRecordTypeNameField);
                String rtNameSpace = (String)sobject.getSObjectField(caseRecordTypeNSField);
                if(rtNameSpace == null) {
                    rtNameSpace = "";
                }else {
                    if(rtNameSpace.equalsIgnoreCase("Industries")) {
                        rtNameSpace = nameSpace;
                    }
                }
                for(int j=0;j<rtList.size();j++) {
                    RecordType currentRT = rtList.get(j);
                    if(rtName.equalsIgnoreCase(currentRT.getRecordTypeName())
                            && currentRT.getsObjectType().equalsIgnoreCase("Case")
                            && currentRT.getIsActive() && rtNameSpace.equalsIgnoreCase(currentRT.getNamespacePrefix())) {

                        IndustriesRecordType aRt = new IndustriesRecordType();
                        aRt.setRecordTypeId(currentRT.getRecordTypeId());
                        aRt.setsObjectType("Case");
                        aRt.setRecordTypeName(rtName);
                        aRt.setNameSpace(rtNameSpace);
                        cRtList.add(aRt);
                        break;
                    }
                }
            }
        }finally {
            pcPool.returnObject(pc);
        }
        return cRtList;
    }
}
