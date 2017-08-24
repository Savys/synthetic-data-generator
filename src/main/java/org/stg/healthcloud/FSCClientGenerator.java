package org.stg.healthcloud;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.stg.pojo.IndividualResult;
import org.stg.service.bulk.BulkProblemGoalTaskService;

import org.stg.service.LayoutMetadataService;
import org.stg.service.bulk.BulkCaseService;
import org.stg.service.bulk.BulkFSCContactService;
import org.stg.service.bulk.BulkHCAccountService;

public class FSCClientGenerator {

    final static Logger logger = Logger.getLogger(FSCClientGenerator.class);
    @Autowired
    BulkHCAccountService bulkHCAccountService;
    @Autowired
    BulkFSCContactService bulkFSCContactService;
    @Autowired
    BulkCaseService bulkCaseService;
    @Autowired
    BulkProblemGoalTaskService bulkProblemGoalTaskService;
    @Autowired
    LayoutMetadataService layoutMetadataService;


    public void start() throws Exception {
        int count=1;

        //List<IndividualResult> indvResults = bulkFSCAccountService.generateAndPush(count);
        List<IndividualResult> indvResults = bulkFSCContactService.generateAndPush(count);

        indvResults.size();
    }

}
