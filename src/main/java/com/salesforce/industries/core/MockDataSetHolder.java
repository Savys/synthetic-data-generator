package com.salesforce.industries.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.salesforce.industries.exception.DAOException;
import com.salesforce.industries.persistence.AddressDAO;
import com.salesforce.industries.persistence.CarePlanDAO;
import com.salesforce.industries.persistence.FirstNameDAO;
import com.salesforce.industries.persistence.LastNameDAO;
import com.salesforce.industries.persistence.ProblemGoalTaskDAO;
import com.salesforce.industries.persistence.model.Address;
import com.salesforce.industries.persistence.model.CarePlan;
import com.salesforce.industries.persistence.model.FirstName;
import com.salesforce.industries.persistence.model.LastName;
import com.salesforce.industries.persistence.model.Problem;

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

    public List<FirstName> firstNameList  = new ArrayList<FirstName> ();
    public List<LastName> lastNameList  = new ArrayList<LastName> ();
    public List<Address> addressList  = new ArrayList<Address> ();
    public Set<String> fullNames = new HashSet<String>();
    public List<CarePlan> carePlanList = new ArrayList<CarePlan>();
    public Map<Integer,List<Problem>> problemList = new HashMap<Integer,List<Problem>>();

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


    }



}
