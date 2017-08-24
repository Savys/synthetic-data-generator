package org.stg.service.generator.account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stg.core.RandUtil;
import org.stg.domain.account.HCAccount;
import org.stg.exception.DAOException;
import org.stg.generator.BaseGenerator;

@Service
public class HCAccountGeneratorService extends BaseGenerator<HCAccount>{

    @Autowired
    AccountGeneratorService accountGeneratorService;
    @Autowired
    IndustriesAccountGeneratorService industriesAccountGeneratorService;

    protected HCAccount getNewRecord() {
        return new HCAccount();
    }

    protected HCAccount populateRecord(HCAccount account, int recNumber,String recordTypeId,String parentId) throws IOException {
        industriesAccountGeneratorService.populateRecord(account, recNumber, recordTypeId,parentId);

        /* Health Cloud Specic data generation logic here */
        account.setSourceSystemId(RandUtil.generateRandomInteger(1000,99999)+"-"+RandUtil.generateRandomInteger(1000,99999)+"-"+RandUtil.generateRandomInteger(1000,99999));
        account.setMedicalRecordNumber(RandUtil.generateRandomInteger(1000,999999)+"-"+RandUtil.generateRandomInteger(1000,9999)+RandUtil.generateRandomInteger(1000,999999));
        account.setSourceSystem("EMR1");
        /* EndOf:  Health Cloud Specic data generation logic here */

        return account;
    }

    public List<HCAccount> generate(int recordCount,String recordTypeId,String nameSpace,String parentId) throws IOException, DAOException {
        List<HCAccount> accounts = new ArrayList<HCAccount>();
        for(int i=0;i<recordCount;i++) {
            accounts.add(populateRecord(getNewRecord(),i,recordTypeId,parentId));
        }
        return accounts;
    }
}
