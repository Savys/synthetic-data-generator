package com.salesforce.industries.healthcloud;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = null;
        try {
            context = new ClassPathXmlApplicationContext("Spring-Module.xml");
            HCPatientGenerator patientGenerator = (HCPatientGenerator)context.getBean("patientGenerator");
            patientGenerator.start();
        }finally {
            if(context != null) {
                context.close();
            }
        }

    }
}