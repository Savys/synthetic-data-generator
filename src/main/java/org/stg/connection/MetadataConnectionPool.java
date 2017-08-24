package org.stg.connection;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.sforce.soap.metadata.MetadataConnection;

public class MetadataConnectionPool extends GenericObjectPool<MetadataConnection>{
    /**
     * Constructor.
     *
     * It uses the default configuration for pool provided by
     * apache-commons-pool2.
     *
     * @param factory
     */
    public MetadataConnectionPool(PooledObjectFactory<MetadataConnection> factory) {
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
    public MetadataConnectionPool(PooledObjectFactory<MetadataConnection> factory, GenericObjectPoolConfig config) {
        super(factory, config);
    }
}
