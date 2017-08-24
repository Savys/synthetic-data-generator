package org.stg.bootstrap;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.Logger;
import org.stg.connection.MetadataConnectionFactory;
import org.stg.connection.MetadataConnectionPool;
import org.stg.connection.PartnerConnectionFactory;
import org.stg.connection.PartnerConnectionPool;

public class Bootstrap  {

    final static Logger logger = Logger.getLogger(Bootstrap.class);

    private static PartnerConnectionPool partnerPool=null;
    private static MetadataConnectionPool metadataPool=null;

    public Bootstrap() {

    }

    public void onInit() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(3);
        config.setMaxTotal(3);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(false);

        partnerPool = new PartnerConnectionPool(new PartnerConnectionFactory(), config);
        metadataPool = new MetadataConnectionPool(new MetadataConnectionFactory(), config);
    }

    public static synchronized PartnerConnectionPool getPartnerConnectionPool() {
        if(partnerPool == null) {
            new Bootstrap();
        }
        return partnerPool;
    }

    public static synchronized MetadataConnectionPool getMetadataConnectionPool() {
        if(metadataPool == null) {
            new Bootstrap();
        }
        return metadataPool;
    }
}
