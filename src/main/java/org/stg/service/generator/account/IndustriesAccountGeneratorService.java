package org.stg.service.generator.account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stg.domain.account.IndustriesAccount;
import org.stg.exception.DAOException;
import org.stg.generator.BaseGenerator;

@Service
public class IndustriesAccountGeneratorService extends BaseGenerator<IndustriesAccount> {

    @Autowired
    AccountGeneratorService accountGeneratorService;

    protected IndustriesAccount getNewRecord() {
        return new IndustriesAccount();
    }

    protected IndustriesAccount populateRecord(IndustriesAccount account, int recNumber,String recordTypeId,String parentId) throws IOException {
        accountGeneratorService.populateRecord(account, recNumber, recordTypeId,parentId);

        //Populate IndustriesAccount specific fields here
        //End: Populate IndustriesAccount specific fields here
        return account;
    }

    public List<IndustriesAccount> generate(int recordCount,String recordTypeId,String nameSpace,String parentId) throws IOException, DAOException {
        List<IndustriesAccount> accounts = new ArrayList<IndustriesAccount>();
        for(int i=0;i<recordCount;i++) {
            accounts.add(populateRecord(getNewRecord(),i,recordTypeId,parentId));
        }
        return accounts;
    }

}
