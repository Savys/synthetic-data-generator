package com.salesforce.industries.connection;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.sforce.soap.partner.PartnerConnection;


public class PartnerConnectionPool extends GenericObjectPool<PartnerConnection>{
    /**
     * Constructor.
     *
     * It uses the default configuration for pool provided by
     * apache-commons-pool2.
     *
     * @param factory
     */
    public PartnerConnectionPool(PooledObjectFactory<PartnerConnection> factory) {
        super(factory);
    }
    /**
     * Constructor.
     *
     * This can be used to have full control over the pool using configuration
     * object.
     *
     * @param factory
     * @param config
     */
    public PartnerConnectionPool(PooledObjectFactory<PartnerConnection> factory, GenericObjectPoolConfig config) {
        super(factory, config);
    }
}