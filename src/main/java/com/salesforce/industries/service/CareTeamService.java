package com.salesforce.industries.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesforce.industries.core.IService;

@Service
public class CareTeamService implements IService {

    final static Logger logger = Logger.getLogger(CareTeamService.class);

    @Autowired
    OrgService orgService;

}
