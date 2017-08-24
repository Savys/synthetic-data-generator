package org.stg.service.generator.account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stg.domain.account.FSCAccount;
import org.stg.exception.DAOException;
import org.stg.generator.BaseGenerator;
import org.stg.service.CustomObjectMetadataService;

@Service
public class FSCAccountGeneratorService extends BaseGenerator<FSCAccount>{

    @Autowired
    AccountGeneratorService accountGeneratorService;
    @Autowired
    IndustriesAccountGeneratorService industriesAccountGeneratorService;
    @Autowired
    CustomObjectMetadataService customObjectMetadataService;

    protected FSCAccount getNewRecord() {
        return new FSCAccount();
    }

    protected FSCAccount populateRecord(FSCAccount account, int recNumber,String recordTypeId,String parentId) throws Exception {
        industriesAccountGeneratorService.populateRecord(account, recNumber, recordTypeId,parentId);

        /* Wealth Cloud Specic data generation logic here */


        /* EndOf:  Weath Cloud Specic data generation logic here */

        return account;
    }

    public List<FSCAccount> generate(int recordCount,String recordTypeId,String nameSpace,String parentId) throws IOException, DAOException, Exception {
        List<FSCAccount> accounts = new ArrayList<FSCAccount>();
        for(int i=0;i<recordCount;i++) {
            accounts.add(populateRecord(getNewRecord(),i,recordTypeId,parentId));
        }
        return accounts;
    }
}
