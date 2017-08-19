package com.salesforce.industries.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesforce.industries.bulk.BulkOperation;
import com.salesforce.industries.core.RandUtil;
import com.salesforce.industries.pojo.BulkResult;
import com.salesforce.industries.pojo.IndividualResult;
import com.salesforce.industries.pojo.IndustriesCarePlan;
import com.salesforce.industries.pojo.IndustriesGoal;
import com.salesforce.industries.pojo.IndustriesProblem;
import com.salesforce.industries.pojo.IndustriesTask;
import com.salesforce.industries.service.OrgService;
import com.sforce.async.OperationEnum;

@Service
public class BulkProblemGoalTaskService {

    final static Logger logger = Logger.getLogger(BulkProblemGoalTaskService.class);

    @Autowired
    OrgService orgService;
    @Autowired
    ProblemGoalTaskGeneratorService problemGoalTaskGeneratorService;

    public List<IndustriesProblem> generateAndPush(List<IndividualResult> indvResults,List<IndustriesCarePlan> carePlans) throws Exception {

        List<IndustriesProblem> problems=null;

        //Get Problem-Goal-Task from Mock Store
        String nameSpace = orgService.getIndustriesNamespace();
        List<IndustriesProblem> allProblems = new ArrayList<IndustriesProblem>();
        for(int j=0;j<carePlans.size();j++) {
            IndustriesCarePlan cp = carePlans.get(j);
            if(cp.getId() != null) {
                //problem
                int problemCount = RandUtil.getRandomNumberInRange(1, 10);
                problems = problemGoalTaskGeneratorService.generate(problemCount, "", nameSpace,cp.getReferenceId());
                for(IndustriesProblem problem: problems) {
                    problem.setCarePlanId(cp.getId());
                    problem.setCcUser(cp.getCcUser());
                }
                allProblems.addAll(problems);
            }
        }

        //Generate Problem
        BulkOperation<IndustriesProblem> problemBulk = new BulkOperation<IndustriesProblem>(orgService.getIndustriesNamespace());
        List<BulkResult> problemResults = problemBulk.push(allProblems,OperationEnum.insert);

        //Generate Goals
        List<IndustriesGoal> allGoals = new ArrayList<IndustriesGoal>();
        for(int i=0;i<problemResults.size();i++) {
            IndustriesProblem problem = allProblems.get(i);
            BulkResult result = problemResults.get(i);
            problem.setId(result.getId());
            for(IndustriesGoal goal: problem.getGoals()) {
                goal.setCarePlanId(problem.getCarePlanId());
                goal.setProblemId(result.getId());
                goal.setCcUser(problem.getCcUser());
                allGoals.add(goal);
            }
        }

        BulkOperation<IndustriesGoal> goalBulk = new BulkOperation<IndustriesGoal>(orgService.getIndustriesNamespace());
        List<BulkResult> goalResults = goalBulk.push(allGoals,OperationEnum.insert);

        //Generate Tasks
        List<IndustriesTask> allTasks = new ArrayList<IndustriesTask>();
        for(int i=0;i<goalResults.size();i++) {
            IndustriesGoal goal = allGoals.get(i);
            BulkResult result = goalResults.get(i);
            goal.setId(result.getId());
            for(IndustriesTask task: goal.getTasks()) {
                task.setCarePlanId(goal.getCarePlanId());
                task.setGoalId(result.getId());
                String status = "Not Started";
                Date genDate = RandUtil.generateRandomDate(2015,10,2020,10);
                Date tDate = RandUtil.getThreshHoldDate(-1);
                if(tDate.getTime() > genDate.getTime() ) {
                    status = "Closed";
                }
                task.setActivityDate(RandUtil.getFormattedDate(genDate));
                task.setAssignedToId(goal.getCcUser());
                task.setProblemId(goal.getProblemId());
                task.setStatus(status);
                task.setAssignedToId(goal.getCcUser());
                allTasks.add(task);
            }
        }

        BulkOperation<IndustriesTask> taskBulk = new BulkOperation<IndustriesTask>(orgService.getIndustriesNamespace());
        List<BulkResult> taskResults = taskBulk.push(allTasks,OperationEnum.insert);
        taskResults.size();

        return problems;

    }
}
