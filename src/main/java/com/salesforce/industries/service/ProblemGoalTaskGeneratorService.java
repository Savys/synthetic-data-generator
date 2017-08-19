package com.salesforce.industries.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.salesforce.industries.exception.DAOException;
import com.salesforce.industries.generator.BaseGenerator;
import com.salesforce.industries.persistence.model.Goal;
import com.salesforce.industries.persistence.model.Problem;
import com.salesforce.industries.persistence.model.Task;
import com.salesforce.industries.pojo.IndustriesGoal;
import com.salesforce.industries.pojo.IndustriesProblem;
import com.salesforce.industries.pojo.IndustriesTask;

public class ProblemGoalTaskGeneratorService extends BaseGenerator<IndustriesProblem> {


    public ProblemGoalTaskGeneratorService() throws DAOException {
        super();
    }

    public List<IndustriesProblem> generate(int recordCount,String recordTypeId,String nameSpace,String parentId) throws IOException, DAOException {
        List<IndustriesProblem> industriesProblems = new ArrayList<IndustriesProblem>();
        Integer carePlanId = Integer.parseInt(parentId);
        List<Problem> problems = mockDataSet.problemList.get(carePlanId);
        if(problems != null) {
            for(int i=0;i<problems.size();i++) {
                Problem problem = problems.get(i);
                IndustriesProblem industriesProblem = new IndustriesProblem();
                industriesProblem.setName(problem.getProblemText());
                List<IndustriesGoal> industriesGoals = new ArrayList<IndustriesGoal>();
                industriesProblem.setGoals(industriesGoals);
                for(Goal goal: problem.getGoals()) {
                    industriesProblem.getGoals();
                    IndustriesGoal industriesGoal = new IndustriesGoal();
                    industriesGoal.setName(goal.getGoalText());
                    List<IndustriesTask> industriesTasks = new ArrayList<IndustriesTask>();
                    industriesGoal.setTasks(industriesTasks);
                    industriesGoals.add(industriesGoal);
                    for(Task task: goal.getTasks()) {
                        IndustriesTask industriesTask = new IndustriesTask();
                        industriesTask.setSubject(task.getTaskText());
                        industriesTasks.add(industriesTask);
                    }
                }
                industriesProblems.add(industriesProblem);
            }
        }
        return industriesProblems;
    }


}
