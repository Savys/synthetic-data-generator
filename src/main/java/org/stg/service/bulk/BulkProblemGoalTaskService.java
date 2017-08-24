package org.stg.service.bulk;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stg.bulk.BulkOperation;
import org.stg.core.MockDataSetHolder;
import org.stg.core.RandUtil;
import org.stg.persistence.model.Task;
import org.stg.pojo.BulkResult;
import org.stg.pojo.IndividualResult;
import org.stg.pojo.IndustriesCarePlan;
import org.stg.pojo.IndustriesGoal;
import org.stg.pojo.IndustriesProblem;
import org.stg.pojo.IndustriesTask;
import org.stg.service.OrgService;
import org.stg.service.generator.ProblemGoalTaskGeneratorService;

import com.sforce.async.OperationEnum;

@Service
public class BulkProblemGoalTaskService {

    final static Logger logger = Logger.getLogger(BulkProblemGoalTaskService.class);

    @Autowired
    OrgService orgService;
    @Autowired
    ProblemGoalTaskGeneratorService problemGoalTaskGeneratorService;
    @Autowired
    MockDataSetHolder mockDataSetHolder;

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
                Calendar today = Calendar.getInstance();
                Calendar genDate = RandUtil.generateRandomCalendarDate(2015,10,2020,10);
                long currentDateDiff = daysBetween(genDate,today);
                if(currentDateDiff > 20 ) {
                    status = "Completed";
                }
                if(task.getIsRecurrence().equalsIgnoreCase("true") && status.equalsIgnoreCase("Not Started") ) {
                    task.setActivityDate("");
                    task.setRecurrenceStartDateOnly(genDate);
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(genDate.getTimeInMillis());
                    cal.add(Calendar.DATE, 90);
                    task.setRecurrenceEndDateOnly(cal);
                    Integer weekDays = 2+4+8+16+32;
                    task.setRecurrenceDayOfWeekMask(weekDays.toString());
                }else {
                    task.setIsRecurrence("false");
                    task.setActivityDate(RandUtil.getFormattedDate(genDate));
                    task.setRecurrenceStartDateOnly(null);
                    task.setRecurrenceEndDateOnly(null);
                    task.setRecurrenceDayOfWeekMask("");
                    task.setRecurrenceType("");
                }
                task.setAssignedToId(goal.getCcUser());
                task.setProblemId(goal.getProblemId());
                task.setStatus(status);
                task.setAssignedToId(goal.getCcUser());
                allTasks.add(task);
            }
        }

        //Generate UnRelated Tasks
        int maxUnrelatedTasks = RandUtil.generateRandomInteger(0, carePlans.size()-1);
        for(int i=0;i<maxUnrelatedTasks;i++) {
            IndustriesCarePlan industriesCarePlan = carePlans.get(i);
            List<Task> unrelatedTasks = mockDataSetHolder.unrelatedCarePlanTaskMap.get(Integer.parseInt(industriesCarePlan.getReferenceId().trim()));
            if(unrelatedTasks != null ) {
                for(Task unrelatedTask :unrelatedTasks ) {
                    IndustriesTask industriesTask = new IndustriesTask();
                    industriesTask.setAccountId(industriesCarePlan.getAccountId());
                    industriesTask.setCarePlanId(industriesCarePlan.getId());
                    industriesTask.setSubject(unrelatedTask.getTaskText());
                    industriesTask.setIsRecurrence(unrelatedTask.getIsRecurrence());
                    industriesTask.setRecurrenceStartDateOnly(null);
                    industriesTask.setRecurrenceEndDateOnly(null);
                    industriesTask.setRecurrenceType("");
                    industriesTask.setRecurrenceInterval("");
                    industriesTask.setProblemId("");
                    industriesTask.setGoalId("");

                    String status = "Not Started";
                    Date genDate = RandUtil.generateRandomDate(2015,10,2020,10);
                    Date tDate = RandUtil.getThreshHoldDate(-1);
                    if(tDate.getTime() > genDate.getTime() ) {
                        status = "Completed";
                    }
                    if(unrelatedTask.getIsRecurrence().equalsIgnoreCase("true") && status.equalsIgnoreCase("Not Started") ) {
                        industriesTask.setIsRecurrence("true");
                        industriesTask.setActivityDate("");
                        if(unrelatedTask.getRecurrenceType().equalsIgnoreCase("RecursEveryWeekday")) {
                            industriesTask.setRecurrenceInterval("");
                        }else {
                            industriesTask.setRecurrenceInterval("100");
                        }
                        industriesTask.setRecurrenceType(unrelatedTask.getRecurrenceType());
                        Calendar startCal = RandUtil.generateRandomCalendarDate(2015,10,2020,10);
                        industriesTask.setRecurrenceStartDateOnly(startCal);

                        Calendar endCal = Calendar.getInstance();
                        endCal.setTime(startCal.getTime());
                        int maxInterval = RandUtil.generateRandomInteger(1, 89);
                        endCal.add(Calendar.DATE, maxInterval);
                        industriesTask.setRecurrenceEndDateOnly(endCal);
                        Integer weekDays = 2+4+8+16+32;
                        industriesTask.setRecurrenceDayOfWeekMask(weekDays.toString());
                    }else {
                        industriesTask.setIsRecurrence("false");
                        industriesTask.setActivityDate(RandUtil.getFormattedDate(genDate));
                        industriesTask.setRecurrenceStartDateOnly(null);
                        industriesTask.setRecurrenceEndDateOnly(null);
                        industriesTask.setRecurrenceDayOfWeekMask("");
                        industriesTask.setRecurrenceType("");
                    }
                    industriesTask.setAssignedToId(industriesCarePlan.getCcUser());
                    industriesTask.setStatus(status);
                    allTasks.add(industriesTask);
                }
            }
        }

        BulkOperation<IndustriesTask> taskBulk = new BulkOperation<IndustriesTask>(orgService.getIndustriesNamespace());

        List<BulkResult> taskResults = taskBulk.push(allTasks,OperationEnum.insert);
        taskResults.size();

        return problems;
    }


    private static long daysBetween(Calendar startDate, Calendar endDate) {
        if(startDate == null) {
            return 10;
        }
        long end = endDate.getTimeInMillis();
        long start = startDate.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(end - start);
    }
}
