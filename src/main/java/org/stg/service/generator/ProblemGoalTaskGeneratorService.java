package org.stg.service.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.stg.exception.DAOException;
import org.stg.generator.BaseGenerator;
import org.stg.persistence.model.Goal;
import org.stg.persistence.model.Problem;
import org.stg.persistence.model.Task;
import org.stg.pojo.IndustriesGoal;
import org.stg.pojo.IndustriesProblem;
import org.stg.pojo.IndustriesTask;

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
                        industriesTask.setIsRecurrence(task.getIsRecurrence());
                        industriesTask.setRecurrenceInterval(task.getRecurrenceInterval());
                        industriesTask.setRecurrenceType(task.getRecurrenceType());
                        industriesTasks.add(industriesTask);
                    }
                }
                industriesProblems.add(industriesProblem);
            }
        }
        return industriesProblems;
    }


}
