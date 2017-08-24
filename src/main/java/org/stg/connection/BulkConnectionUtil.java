package org.stg.connection;

import com.sforce.async.BulkConnection;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectorConfig;

public class BulkConnectionUtil {

    public BulkConnection get(PartnerConnection pc) throws Exception {

        // When PartnerConnection is instantiated, a login is implicitly
        // executed and, if successful,
        // a valid session is stored in the ConnectorConfig instance.
        // Use this key to initialize a BulkConnection:
        ConnectorConfig partnerConfig = pc.getConfig();
        ConnectorConfig bulkConfig = new ConnectorConfig();

        bulkConfig.setSessionId(partnerConfig.getSessionId());
        // The endpoint for the Bulk API service is the same as for the normal
        // SOAP uri until the /Soap/ part. From here it's '/async/versionNumber'
        String soapEndpoint = partnerConfig.getServiceEndpoint();
        String apiVersion = "40.0";
        String restEndpoint = soapEndpoint.substring(0, soapEndpoint.indexOf("Soap/"))
                + "async/" + apiVersion;
        bulkConfig.setRestEndpoint(restEndpoint);
        // This should only be false when doing debugging.
        bulkConfig.setCompression(true);
        // Set this to true to see HTTP requests and responses on stdout
        bulkConfig.setTraceMessage(false);
        BulkConnection connection = new BulkConnection(bulkConfig);
        return connection;
    }

}
