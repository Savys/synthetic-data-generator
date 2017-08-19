package com.salesforce.industries.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Service;

import com.salesforce.industries.bootstrap.Bootstrap;
import com.salesforce.industries.connection.PartnerConnectionPool;
import com.salesforce.industries.core.IService;
import com.salesforce.industries.pojo.User;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.sobject.SObject;

@Service
public class UserService implements IService {

    final static Logger logger = Logger.getLogger(UserService.class);

    public List<User> getUsersByRole(String roleName) throws Exception {
        PartnerConnectionPool pcPool=Bootstrap.getPartnerConnectionPool();
        PartnerConnection pc = pcPool.borrowObject();
        List<User> users = new ArrayList<User>();
        try {
            String soql = "select Id, UserRoleId, UserType,Username,Email,IsActive, UserRole.Name,AccountId,ContactId from User where UserType='Standard' and UserRole.Name = '" + roleName + "' and IsActive = true" ;
            QueryResult qr = pc.query(soql);

            SObject[] sobjects = qr.getRecords();
            for(int i=0;i<sobjects.length;i++) {
                SObject sobject = sobjects[i];

                User user = new User();
                user.setId(sobject.getId());
                user.setUserRoleId((String)sobject.getSObjectField("UserRoleId"));
                user.setUserType((String)sobject.getSObjectField("UserType"));
                user.setUsername((String)sobject.getSObjectField("Username"));
                user.setEmail((String)sobject.getSObjectField("Email"));
                user.setRoleName((String)sobject.getSObjectField("UserRole.Name"));
                user.setAccountId((String)sobject.getSObjectField("AccountId"));
                user.setContactId((String)sobject.getSObjectField("ContactId"));
                users.add(user);
            }
        }catch (Exception e) {
            throw e;
        } finally {
            if (pc != null) {
                pcPool.returnObject(pc);
            }
        }

        return users;
    }

    public List<User> getUsers() throws Exception {
        PartnerConnectionPool pcPool=Bootstrap.getPartnerConnectionPool();
        PartnerConnection pc = pcPool.borrowObject();
        List<User> users = new ArrayList<User>();
        try {
            String soql = "select Id, UserRoleId, UserType,Username,Email,IsActive, UserRole.Name,AccountId,ContactId from User where UserType='Standard' and IsActive = true limit 9999" ;
            QueryResult qr = pc.query(soql);

            SObject[] sobjects = qr.getRecords();
            for(int i=0;i<sobjects.length;i++) {
                SObject sobject = sobjects[i];

                User user = new User();
                user.setId(sobject.getId());
                user.setUserRoleId((String)sobject.getSObjectField("UserRoleId"));
                user.setUserType((String)sobject.getSObjectField("UserType"));
                user.setUsername((String)sobject.getSObjectField("Username"));
                user.setEmail((String)sobject.getSObjectField("Email"));
                user.setRoleName((String)sobject.getSObjectField("UserRole.Name"));
                user.setAccountId((String)sobject.getSObjectField("AccountId"));
                user.setContactId((String)sobject.getSObjectField("ContactId"));
                users.add(user);
            }
        }catch (Exception e) {
            throw e;
        } finally {
            if (pc != null) {
                pcPool.returnObject(pc);
            }
        }

        return users;
    }

}
