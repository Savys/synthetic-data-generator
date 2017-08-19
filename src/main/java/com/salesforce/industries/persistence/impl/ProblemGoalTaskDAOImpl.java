package com.salesforce.industries.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.salesforce.industries.core.DBUtil;
import com.salesforce.industries.exception.DAOException;
import com.salesforce.industries.persistence.ProblemGoalTaskDAO;
import com.salesforce.industries.persistence.model.CarePlan;
import com.salesforce.industries.persistence.model.Goal;
import com.salesforce.industries.persistence.model.Problem;
import com.salesforce.industries.persistence.model.Task;

@Repository
public class ProblemGoalTaskDAOImpl implements ProblemGoalTaskDAO {

    @Autowired
    private DataSource mockDataSource;

    //
    // static data
    //
    protected static List<String> pkColumns = new ArrayList<String>();
    protected static List<String> stdColumns = new ArrayList<String>();
    protected static List<String> allColumns = new ArrayList<String>();
    protected static String tableName = "CarePlans";

    static
    {
        pkColumns.add("id");
        stdColumns.add("Name");
        allColumns.addAll(pkColumns);
        allColumns.addAll(stdColumns);
    }

    public ProblemGoalTaskDAOImpl()
    {
    }



    public Map<Integer,List<Problem>> selectAll() throws DAOException
    {
        Map<Integer,List<Problem>> problemCarePlanMap = new HashMap<Integer,List<Problem>>();
        Map<Integer,Problem> problemMap = new HashMap<Integer,Problem>();
        Map<Integer,Set<Goal>> goalMap = new HashMap<Integer,Set<Goal>>();
        Map<Integer,Set<Task>> taskMap = new HashMap<Integer,Set<Task>>();

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try
        {
            connection = mockDataSource.getConnection();
            String sql = "select Problem.CarePlanId,Problem.ProblemId,Problem.ProblemText,Goal.GoalId, Goal.GoalText,Tasks.TaskId, Tasks.TaskText from Problem join Goal on Problem.ProblemId = Goal.ProblemId join Tasks on Tasks.GoalId = Goal.GoalId order by Problem.ProblemId,Goal.GoalId";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Problem problem = new Problem();
                problem.setProblemId(DBUtil.getInteger(rs, "ProblemId"));
                problem.setProblemText(DBUtil.getString(rs, "ProblemText"));
                problem.setCarePlanId(DBUtil.getInteger(rs, "CarePlanId"));
                problemMap.put(DBUtil.getInteger(rs, "ProblemId"),problem);

                Goal goal = new Goal();
                goal.setGoalId(DBUtil.getInteger(rs, "GoalId"));
                goal.setGoalText(DBUtil.getString(rs, "GoalText"));
                if(goalMap.containsKey(DBUtil.getInteger(rs, "ProblemId"))) {
                    Set<Goal> goalSet = goalMap.get(DBUtil.getInteger(rs, "ProblemId"));
                    goalSet.add(goal);
                }else {
                    Set<Goal> goalSet = new HashSet<Goal>();
                    goalSet.add(goal);
                    goalMap.put(DBUtil.getInteger(rs, "ProblemId"), goalSet);
                }

                Task task = new Task();
                task.setTaskId(DBUtil.getInteger(rs, "TaskId"));
                task.setTaskText(DBUtil.getString(rs, "TaskText"));
                if(taskMap.containsKey(DBUtil.getInteger(rs, "GoalId"))) {
                    Set<Task> taskSet = taskMap.get(DBUtil.getInteger(rs, "GoalId"));
                    taskSet.add(task);
                }else {
                    Set<Task> taskSet = new HashSet<Task>();
                    taskSet.add(task);
                    taskMap.put(DBUtil.getInteger(rs, "GoalId"), taskSet);
                }
            }
            for (Map.Entry<Integer, Problem> entry : problemMap.entrySet())
            {
                Problem problem = entry.getValue();
                Set<Goal> goalSet = goalMap.get(problem.getProblemId());
                if(goalSet != null) {
                    List<Goal> goalList = new ArrayList<Goal>();
                    goalList.addAll(goalSet);
                    problem.setGoals(goalList);
                    for(Goal goal:goalList) {
                        Set<Task> taskSet = taskMap.get(goal.getGoalId());
                        if(taskSet != null) {
                            List<Task> taskList = new ArrayList<Task>();
                            taskList.addAll(taskSet);
                            goal.setTasks(taskList);
                        }
                    }
                }
                if(problemCarePlanMap.containsKey(problem.getCarePlanId())) {
                    List<Problem> problems =  problemCarePlanMap.get(problem.getCarePlanId());
                    problems.add(problem);
                }else {
                    List<Problem> problems = new ArrayList<Problem>();
                    problems.add(problem);
                    problemCarePlanMap.put(problem.getCarePlanId(), problems);
                }
            }

            for (Map.Entry<Integer, Problem> entry : problemMap.entrySet()) {
                Problem problem = entry.getValue();
                List<Goal> goals = problem.getGoals();
                for(Goal goal : goals) {
                    List<Task> tasks = goal.getTasks();
                    for(Task task: tasks) {
                        System.out.println(task);
                    }
                }
            }

        }
        catch (SQLException e)
        {
            throw new DAOException(e);
        }
        finally
        {
            DBUtil.close(connection,ps, rs);
        }

        return problemCarePlanMap;
    }

    protected CarePlan fromResultSet(ResultSet rs) throws SQLException
    {
        CarePlan obj = new CarePlan();
        obj.setSubject(DBUtil.getString(rs, "Name"));
        return obj;
    }

}
