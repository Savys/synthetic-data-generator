package org.stg.service.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.stg.core.RandomListUtil;
import org.stg.exception.DAOException;
import org.stg.generator.BaseGenerator;
import org.stg.persistence.model.CarePlan;

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
