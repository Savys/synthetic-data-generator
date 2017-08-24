package org.stg.connection;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.log4j.Logger;

import com.sforce.soap.metadata.MetadataConnection;

/**
 * Factory to create Partner Connection object(s).
 *
 * @author sriram gopalan
 *
 */
public class MetadataConnectionFactory extends BasePooledObjectFactory<MetadataConnection> {
    final static Logger logger = Logger.getLogger(MetadataConnectionFactory.class);

    @Override
    public MetadataConnection create() throws Exception {
        return new MetadataLoginUtil().getConnection();
    }

    @Override
    public PooledObject<MetadataConnection> wrap(MetadataConnection connection) {
        return new DefaultPooledObject<MetadataConnection>(connection);
    }

    @Override
    public void passivateObject(PooledObject<MetadataConnection> connection) throws Exception {
        connection.getObject();
    }

    @Override
    public boolean validateObject(PooledObject<MetadataConnection> connection) {
        Boolean result = true;
        try {
            connection.getObject().readMetadata("CustomObject", new String[] {"Account" });
        }catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }


}