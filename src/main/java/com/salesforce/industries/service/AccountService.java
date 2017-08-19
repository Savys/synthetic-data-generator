package com.salesforce.industries.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesforce.industries.bootstrap.Bootstrap;
import com.salesforce.industries.connection.PartnerConnectionPool;
import com.salesforce.industries.core.OrgUtils;
import com.salesforce.industries.pojo.Account;
import com.salesforce.industries.core.IService;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.sobject.SObject;

@Service
public class AccountService implements IService {

    final static Logger logger = Logger.getLogger(AccountService.class);

    @Autowired
    OrgService orgService;

    public List<Account> getByIds(List<String> accountIds) throws Exception {
        List<Account> accounts = new ArrayList<Account>();

        StringBuilder sb= new StringBuilder();
        String filter = "";
        for(int i=0;i<accountIds.size();i++){
            sb.append( "'"+accountIds.get(i)+"'," );
        }
        filter = sb.toString();
        filter = filter.substring(0, filter.length()-1);


        PartnerConnectionPool pcPool=Bootstrap.getPartnerConnectionPool();
        PartnerConnection pc = pcPool.borrowObject();
        try {
            String nameSpace = orgService.getIndustriesNamespace();

            String soql = "select Id,"+ OrgUtils.getFQFieldName(nameSpace,"PrimaryContact__c")+" from Account where Id in (" + filter + ")" ;
            QueryResult qr = pc.query(soql);

            SObject[] sobjects = qr.getRecords();
            for(int i=0;i<sobjects.length;i++) {
                SObject sobject = sobjects[i];

                Account account = new Account();
                account.setId(sobject.getId());
                account.setPrimaryContactId((String)sobject.getSObjectField(OrgUtils.getFQFieldName(nameSpace,"PrimaryContact__c")));

                accounts.add(account);
            }
        }catch (Exception e) {
            throw e;
        } finally {
            if (pc != null) {
                pcPool.returnObject(pc);
            }
        }

        return accounts;
    }
}
