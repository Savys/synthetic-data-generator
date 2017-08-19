package com.salesforce.industries.connection;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;

import com.salesforce.industries.core.ConfigPropertyUtil;
import com.salesforce.industries.core.Consts;
import com.salesforce.industries.pojo.LoginProperty;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public class PartnerLoginUtil {

    final static Logger logger = Logger.getLogger(PartnerLoginUtil.class);

    protected PartnerConnection createConnection() throws Exception {
        logger.debug("Calling PartnerConnection");
        PartnerConnection partnerConnection=null;
        try {
            LoginProperty loginPropery = ConfigPropertyUtil.getInstance().getOrgLoginProperty(Consts.PARTNER_CONNECTION);
            ConnectorConfig config = new ConnectorConfig();
            config.setUsername(loginPropery.getUserName());
            config.setPassword(loginPropery.getPassword());
            config.setAuthEndpoint(loginPropery.getLoginEndPoint());
            //config.setAuthEndpoint(loginPropery.getEndPoint());

            config.setTraceFile("traceLogs.txt");
            config.setTraceMessage(true);
            config.setPrettyPrintXml(true);
            config.setConnectionTimeout(10000);
            config.setReadTimeout(10000);

            partnerConnection = new PartnerConnection(config);

            partnerConnection.login(loginPropery.getUserName(), loginPropery.getPassword());
        } catch (ConnectionException ce) {
            ce.printStackTrace();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        return partnerConnection;
    }

}
