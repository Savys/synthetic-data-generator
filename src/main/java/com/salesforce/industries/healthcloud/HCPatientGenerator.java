package com.salesforce.industries.healthcloud;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.salesforce.industries.pojo.IndividualResult;
import com.salesforce.industries.pojo.IndustriesCarePlan;
import com.salesforce.industries.pojo.IndustriesProblem;
import com.salesforce.industries.service.BulkAccountService;
import com.salesforce.industries.service.BulkCaseService;
import com.salesforce.industries.service.BulkContactService;
import com.salesforce.industries.service.BulkProblemGoalTaskService;

public class HCPatientGenerator {

    final static Logger logger = Logger.getLogger(HCPatientGenerator.class);
    @Autowired
    BulkAccountService bulkAccountService;
    @Autowired
    BulkContactService bulkContactService;
    @Autowired
    BulkCaseService bulkCaseService;
    @Autowired
    BulkProblemGoalTaskService bulkProblemGoalTaskService;


    public void start() throws Exception {
        int count=10;

        List<IndividualResult> indvResults = null;
        indvResults = bulkAccountService.generateAndPush(count);
        //indvResults = bulkContactService.generateAndPush(count);

        List<IndustriesCarePlan> carePlans = bulkCaseService.generateAndPush(indvResults);
        List<IndustriesProblem> industriesProblem = bulkProblemGoalTaskService.generateAndPush(indvResults, carePlans);
        industriesProblem.size();
    }

}
