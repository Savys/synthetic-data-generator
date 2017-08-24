package org.stg.healthcloud;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = null;
        try {
            context = new ClassPathXmlApplicationContext("Spring-Module.xml");

            HCPatientGenerator patientGenerator = (HCPatientGenerator)context.getBean("patientGenerator");
            patientGenerator.start();

            //FSCClientGenerator clientGenerator = (FSCClientGenerator)context.getBean("clientGenerator");
            //clientGenerator.start();


        }finally {
            if(context != null) {
                context.close();
            }
        }

    }
}