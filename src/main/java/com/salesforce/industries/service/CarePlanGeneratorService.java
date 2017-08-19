package com.salesforce.industries.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.salesforce.industries.core.RandomListUtil;
import com.salesforce.industries.exception.DAOException;
import com.salesforce.industries.generator.BaseGenerator;
import com.salesforce.industries.persistence.model.CarePlan;

public class CarePlanGeneratorService extends BaseGenerator<CarePlan> {


    public CarePlanGeneratorService() throws DAOException {
        super();
    }


    public List<CarePlan> generate(int recordCount,String recordTypeId,String nameSpace,String parentId) throws IOException, DAOException {
        RandomListUtil<CarePlan> rlCarePlan = new RandomListUtil<CarePlan>();
        rlCarePlan.reset(mockDataSet.carePlanList);

        List<CarePlan> carePlans = new ArrayList<CarePlan>();
        for(int i=0;i<recordCount;i++) {
            CarePlan selectedItem = rlCarePlan.getRandomItem(mockDataSet.carePlanList);
            if(selectedItem == null) {
                rlCarePlan.reset(mockDataSet.carePlanList);
                selectedItem = rlCarePlan.getRandomItem(mockDataSet.carePlanList);
            }else {
                CarePlan newCarePlan = new CarePlan();
                newCarePlan.setId(selectedItem.getId());
                newCarePlan.setSubject(selectedItem.getSubject());
                newCarePlan.setSubject("CarePlan: " + selectedItem.getSubject());
                carePlans.add(newCarePlan);
            }
        }
        return carePlans;
    }

}
