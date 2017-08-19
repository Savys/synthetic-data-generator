package com.salesforce.industries.bootstrap;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import com.salesforce.industries.connection.MetadataConnectionFactory;
import com.salesforce.industries.connection.MetadataConnectionPool;
import com.salesforce.industries.connection.PartnerConnectionFactory;
import com.salesforce.industries.connection.PartnerConnectionPool;

public class Bootstrap implements InitializingBean {

    final static Logger logger = Logger.getLogger(Bootstrap.class);

    private static PartnerConnectionPool partnerPool=null;
    private static MetadataConnectionPool metadataPool=null;

    public Bootstrap() {

    }

    private void onInit() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(10);
        config.setMaxTotal(10);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(false);

        partnerPool = new PartnerConnectionPool(new PartnerConnectionFactory(), config);
        metadataPool = new MetadataConnectionPool(new MetadataConnectionFactory(), config);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.debug("Inside afterPropertiesSet");
        onInit();
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
