package org.stg.service.generator.account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.stg.core.RandUtil;
import org.stg.domain.account.Account;
import org.stg.exception.DAOException;
import org.stg.generator.BaseGenerator;
import org.stg.persistence.model.Address;
import org.stg.persistence.model.FirstName;
import org.stg.persistence.model.LastName;

@Service
public class AccountGeneratorService extends BaseGenerator<Account> {

    protected Account getNewRecord() {
        return new Account();
    }

    protected Account populateRecord(Account account, int recNumber,String recordTypeId,String parentId) throws IOException {
        int tryCount = 0;

        Integer firstNameRand = RandUtil.generateRandomInteger(0,mockDataSet.firstNameList.size()-1);
        Integer lastNameRand = RandUtil.generateRandomInteger(0,mockDataSet.lastNameList.size()-1);
        FirstName firstName = mockDataSet.firstNameList.get(firstNameRand);
        LastName lastName =  mockDataSet.lastNameList.get(lastNameRand);
        String fullName =firstName.getName() + " " + lastName.getLastname();

        while(tryCount < 100) {
            firstNameRand = RandUtil.generateRandomInteger(0,mockDataSet.firstNameList.size()-1);
            lastNameRand = RandUtil.generateRandomInteger(0,mockDataSet.lastNameList.size()-1);
            firstName = mockDataSet.firstNameList.get(firstNameRand);
            lastName = mockDataSet.lastNameList.get(lastNameRand);

            fullName =firstName.getName() + " " + lastName.getLastname();
            if(mockDataSet.fullNames.contains(fullName)) {
                tryCount++;
                continue;
            }else {
                mockDataSet.fullNames.add(fullName);
                break;
            }
        }

        Address address = mockDataSet.addressList.get(RandUtil.generateRandomInteger(0,mockDataSet.addressList.size()-1));
        int houseNumber = RandUtil.generateRandomInteger(address.getHousenumberstart(),address.getHousenumberend());
        int aptNumber = RandUtil.generateRandomInteger(1,250);

        account.setNames(firstName.getName(),lastName.getLastname());

        account.setStreetNumber(houseNumber);
        account.setStreet(address.getStreet() + " #"+aptNumber);

        account.setCity(address.getCity());
        account.setPostalCode(address.getPostalcode());
        account.setState(address.getState());
        account.setRecordTypeId(recordTypeId);
        return account;
    }

    public List<Account> generate(int recordCount,String recordTypeId,String nameSpace,String parentId) throws IOException, DAOException {
        List<Account> accounts = new ArrayList<Account>();
        for(int i=0;i<recordCount;i++) {
            accounts.add(populateRecord(getNewRecord(),i,recordTypeId,parentId));
        }
        return accounts;
    }

}
