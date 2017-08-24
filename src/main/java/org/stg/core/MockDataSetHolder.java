package org.stg.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.stg.exception.DAOException;
import org.stg.persistence.AddressDAO;
import org.stg.persistence.BusinessNameDAO;
import org.stg.persistence.CarePlanDAO;
import org.stg.persistence.FirstNameDAO;
import org.stg.persistence.LastNameDAO;
import org.stg.persistence.OccupationDAO;
import org.stg.persistence.ProblemGoalTaskDAO;
import org.stg.persistence.model.Address;
import org.stg.persistence.model.BusinessName;
import org.stg.persistence.model.CarePlan;
import org.stg.persistence.model.FirstName;
import org.stg.persistence.model.LastName;
import org.stg.persistence.model.Occupation;
import org.stg.persistence.model.Problem;
import org.stg.persistence.model.Task;

public class MockDataSetHolder {
    final static Logger logger = Logger.getLogger(MockDataSetHolder.class);

    @Autowired
    FirstNameDAO firstNameDAO;
    @Autowired
    LastNameDAO lastNameDAO;
    @Autowired
    AddressDAO addressDAO;
    @Autowired
    CarePlanDAO carePlanDAO;
    @Autowired
    ProblemGoalTaskDAO problemGoalTaskDAO;
    @Autowired
    BusinessNameDAO businessNameDAO;
    @Autowired
    OccupationDAO occupationDAO;

    public List<FirstName> firstNameList  = new ArrayList<FirstName> ();
    public List<LastName> lastNameList  = new ArrayList<LastName> ();
    public List<Address> addressList  = new ArrayList<Address> ();
    public Set<String> fullNames = new HashSet<String>();
    public List<CarePlan> carePlanList = new ArrayList<CarePlan>();
    public Map<Integer,List<Problem>> problemList = new HashMap<Integer,List<Problem>>();
    public Map<Integer,List<Task>> unrelatedCarePlanTaskMap = new HashMap<Integer,List<Task>>();
    public List<String> countryList;

    public List<BusinessName> businessNameList  = new ArrayList<BusinessName> ();
    public List<BusinessName> fortune200List  = new ArrayList<BusinessName> ();
    public List<Occupation> allOccupation = new ArrayList<Occupation>();
    public List<Occupation> occupationWithSalary = new ArrayList<Occupation>();

    MockDataSetHolder() {
    }

    public void init() throws DAOException {
        logger.debug("Fetching FirstName");
        firstNameList = firstNameDAO.selectAll();

        logger.debug("Fetching LastName");
        lastNameList = lastNameDAO.selectAll();

        logger.debug("Fetching Address");
        addressList = addressDAO.selectAll();

        logger.debug("Fetching CarePlans");
        carePlanList = carePlanDAO.selectAll();

        logger.debug("Fetching Problem/Goal/Task");
        problemList = problemGoalTaskDAO.selectAll();

        logger.debug("Fetching Unrelated Task");
        unrelatedCarePlanTaskMap = problemGoalTaskDAO.selectAllUnrelatedCarePlanTasks();

        CountryUtil cu = new CountryUtil();
        countryList = cu.countryNames;

        logger.debug("Fetching BusinessNames");
        businessNameList = businessNameDAO.selectAll();

        logger.debug("Fetching fortune 200 Names");
        fortune200List = businessNameDAO.selectFortune(200);

        logger.debug("Fetching Occupations");
        allOccupation = occupationDAO.selectAll();

        logger.debug("Fetching Salried Occupations");
        occupationWithSalary = occupationDAO.selectWithSalary();
    }



}
