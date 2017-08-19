package com.salesforce.industries.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.salesforce.industries.bootstrap.Bootstrap;
import com.salesforce.industries.connection.PartnerConnectionPool;
import com.salesforce.industries.core.IService;
import com.salesforce.industries.pojo.CaseTeamRole;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.sobject.SObject;

@Service
public class CaseTeamRoleService implements IService  {

    final static Logger logger = Logger.getLogger(CaseTeamRoleService.class);

    protected static LoadingCache<String, List<CaseTeamRole>> cache;

    public CaseTeamRoleService() {
        buildCache();
    }

    private void buildCache() {
        cache = CacheBuilder.newBuilder().expireAfterWrite(100, TimeUnit.MINUTES)
                .maximumSize(30)
                .build(new CacheLoader<String, List<CaseTeamRole>>() {
                    @Override
                    public List<CaseTeamRole> load(String key) throws Exception {
                        return getCaseTeamRole(key);
                    }
                });
    }

    public List<CaseTeamRole> getCaseTeamRoles(String key) throws Exception {
        return cache.get(key);
    }

    public CaseTeamRole getRoleId(String role) throws Exception {
        List<CaseTeamRole> caseTeamRoles =  getCaseTeamRoles("ROLES");
        CaseTeamRole caseTeamRole=null;
        for(int i=0;i<caseTeamRoles.size();i++) {
            caseTeamRole = caseTeamRoles.get(i);
            if(caseTeamRole.getName().equalsIgnoreCase(role)) {
                break;
            }
        }
        return caseTeamRole;
    }

    public List<CaseTeamRole> getNonPatientRoles() throws Exception {
        List<CaseTeamRole> caseTeamRoles =  getCaseTeamRoles("ROLES");
        List<CaseTeamRole> nonPatientRoles =  new ArrayList<CaseTeamRole>();

        CaseTeamRole caseTeamRole=null;
        for(int i=0;i<caseTeamRoles.size();i++) {
            caseTeamRole = caseTeamRoles.get(i);
            if(caseTeamRole.getName().equalsIgnoreCase("Patient") || caseTeamRole.getName().equalsIgnoreCase("Care Coordinator") ) {

            }else {
                nonPatientRoles.add(caseTeamRole);
            }
        }
        return nonPatientRoles;
    }

    private List<CaseTeamRole> getCaseTeamRole(String key) throws Exception {
        List<CaseTeamRole> caseTeamRoles = new ArrayList<CaseTeamRole>();
        String soql="select Name,AccessLevel, Id from CaseTeamRole";

        PartnerConnectionPool pcPool=Bootstrap.getPartnerConnectionPool();
        PartnerConnection pc = pcPool.borrowObject();

        try {
            QueryResult qr = pc.query(soql);
            SObject[] sobjects = qr.getRecords();
            for(int i=0;i<sobjects.length;i++) {
                SObject sobject = sobjects[i];

                CaseTeamRole caseTeamRole = new CaseTeamRole();
                caseTeamRole.setAccessLevel((String)sobject.getSObjectField("AccessLevel"));
                caseTeamRole.setId((String)sobject.getSObjectField("Id"));
                caseTeamRole.setName((String)sobject.getSObjectField("Name"));
                caseTeamRoles.add(caseTeamRole);
            }
        }finally {
            pcPool.returnObject(pc);
        }


        return caseTeamRoles;
    }

}
