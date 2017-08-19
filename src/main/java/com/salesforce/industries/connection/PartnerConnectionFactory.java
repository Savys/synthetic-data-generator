package com.salesforce.industries.connection;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;

/**
 * Factory to create Partner Connection object(s).
 *
 * @author sriram gopalan
 *
 */
public class PartnerConnectionFactory extends BasePooledObjectFactory<PartnerConnection> {

    @Override
    public PartnerConnection create() throws Exception {
        return new PartnerLoginUtil().createConnection();
    }

    @Override
    public PooledObject<PartnerConnection> wrap(PartnerConnection connection) {
        return new DefaultPooledObject<PartnerConnection>(connection);
    }

    @Override
    public boolean validateObject(PooledObject<PartnerConnection> pc) {
        Boolean result = true;
        String soql = "select Id from Contact limit 1" ;
        try {
            pc.getObject().query(soql);
        } catch (ConnectionException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }

}