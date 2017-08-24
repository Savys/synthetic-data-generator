package org.stg.connection;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.stg.core.ConfigPropertyUtil;
import org.stg.core.Consts;
import org.stg.pojo.LoginProperty;

import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.soap.partner.LoginResult;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public class MetadataLoginUtil {
    final static Logger logger = Logger.getLogger(MetadataLoginUtil.class);

    protected MetadataConnection getConnection() throws Exception {
        try {
            logger.debug("Calling createMetadataConnection");
            return createMetadataConnection();
        } catch (ConnectionException e) {
            throw e;
        }
    }

    private MetadataConnection createMetadataConnection() throws Exception {

        LoginResult loginResult = loginToSalesforce();
        final ConnectorConfig config = new ConnectorConfig();
        config.setServiceEndpoint(loginResult.getMetadataServerUrl());
        config.setSessionId(loginResult.getSessionId());
        return new MetadataConnection(config);
    }

    private LoginResult loginToSalesforce() throws Exception {
        LoginResult lr = null;
        logger.debug("Login To Salesforce");
        try {
            LoginProperty loginPropery = ConfigPropertyUtil.getInstance().getOrgLoginProperty(Consts.PARTNER_CONNECTION);
            ConnectorConfig config = new ConnectorConfig();
            config.setUsername(loginPropery.getUserName());
            config.setPassword(loginPropery.getPassword());
            config.setAuthEndpoint(loginPropery.getLoginEndPoint());

            config.setTraceFile("traceLogs.txt");
            config.setTraceMessage(true);
            config.setPrettyPrintXml(true);
            config.setConnectionTimeout(10000);
            config.setReadTimeout(10000);

            PartnerConnection partnerConnection = new PartnerConnection(config);
            lr = partnerConnection.login(loginPropery.getUserName(), loginPropery.getPassword());
        } catch (ConnectionException ce) {
            throw ce;
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            throw fnfe;
        }
        return lr;
    }


    public static void main(String[] args) {
    }

}
