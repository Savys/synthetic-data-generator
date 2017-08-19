package com.salesforce.industries.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesforce.industries.bootstrap.Bootstrap;
import com.salesforce.industries.connection.PartnerConnectionPool;
import com.salesforce.industries.core.IService;
import com.salesforce.industries.pojo.Contact;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.sobject.SObject;

@Service
public class ContactService implements IService {

    final static Logger logger = Logger.getLogger(ContactService.class);

    @Autowired
    OrgService orgService;

    public List<Contact> getByIds(List<String> contactIds) throws Exception {
        List<Contact> contacts = new ArrayList<Contact>();

        StringBuilder sb= new StringBuilder();
        String filter = "";
        for(int i=0;i<contactIds.size();i++){
            sb.append( "'"+contactIds.get(i)+"'," );
        }
        filter = sb.toString();
        filter = filter.substring(0, filter.length()-1);


        PartnerConnectionPool pcPool=Bootstrap.getPartnerConnectionPool();
        PartnerConnection pc = pcPool.borrowObject();
        try {

            String soql = "select Id,AccountId from Contact where Id in (" + filter + ")" ;
            QueryResult qr = pc.query(soql);

            SObject[] sobjects = qr.getRecords();
            for(int i=0;i<sobjects.length;i++) {
                SObject sobject = sobjects[i];

                Contact contact = new Contact();
                contact.setId(sobject.getId());
                contact.setAccountId((String)sobject.getSObjectField("AccountId"));

                contacts.add(contact);
            }
        }catch (Exception e) {
            throw e;
        } finally {
            if (pc != null) {
                pcPool.returnObject(pc);
            }
        }

        return contacts;
    }

    public List<Contact> getByAccountIds(List<String> accountIds) throws Exception {
        List<Contact> contacts = new ArrayList<Contact>();

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

            String soql = "select Id,AccountId from Contact where AccountId in (" + filter + ")" ;
            QueryResult qr = pc.query(soql);

            SObject[] sobjects = qr.getRecords();
            for(int i=0;i<sobjects.length;i++) {
                SObject sobject = sobjects[i];

                Contact contact = new Contact();
                contact.setId(sobject.getId());
                contact.setAccountId((String)sobject.getSObjectField("AccountId"));

                contacts.add(contact);
            }
        }catch (Exception e) {
            throw e;
        } finally {
            if (pc != null) {
                pcPool.returnObject(pc);
            }
        }

        return contacts;
    }
}
