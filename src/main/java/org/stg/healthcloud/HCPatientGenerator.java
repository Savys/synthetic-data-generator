package org.stg.healthcloud;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.stg.pojo.IndividualResult;
import org.stg.pojo.IndustriesCarePlan;
import org.stg.pojo.IndustriesProblem;
import org.stg.service.bulk.BulkHCContactService;
import org.stg.service.bulk.BulkProblemGoalTaskService;
import org.stg.service.bulk.BulkCaseService;
import org.stg.service.bulk.BulkHCAccountService;

public class HCPatientGenerator {

    final static Logger logger = Logger.getLogger(HCPatientGenerator.class);
    @Autowired
    BulkHCAccountService bulkHCAccountService;
    @Autowired
    BulkHCContactService bulkHCContactService;
    @Autowired
    BulkCaseService bulkCaseService;
    @Autowired
    BulkProblemGoalTaskService bulkProblemGoalTaskService;


    @SuppressWarnings("unused")
    public void start() throws Exception {
        int count=10;

        List<IndividualResult> indvResults = null;
        //indvResults = bulkHCAccountService.generateAndPush(count);
        indvResults = bulkHCContactService.generateAndPush(count);

        List<IndustriesCarePlan> carePlans = bulkCaseService.generateAndPush(indvResults);
        List<IndustriesProblem> industriesProblem = bulkProblemGoalTaskService.generateAndPush(indvResults, carePlans);
    }

}
