package org.stg.generator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.stg.core.RandUtil;
import org.stg.exception.DAOException;
import org.stg.persistence.MedicalschoolDAO;
import org.stg.persistence.PractitionerDAO;
import org.stg.persistence.impl.MedicalschoolDAOImpl;
import org.stg.persistence.impl.PractitionerDAOImpl;
import org.stg.persistence.model.Medicalschool;
import org.stg.persistence.model.Practitioner;
import org.stg.persistence.model.PractitionerIdentity;
import org.stg.persistence.model.PractitionerQualification;

public class PractitionerGenerator {

    public List<Practitioner> get() throws DAOException  {
        PractitionerDAO dao = new PractitionerDAOImpl();

        List<Practitioner> practitioners = dao.selectAll();
        for(int i=0;i<practitioners.size();i++) {
            Practitioner p = practitioners.get(i);
            p.setSourcesystem("EMR1");
            p.setEffectiveStart(RandUtil.generateRandomDate(2010, 2014));
            p.setEffectiveEnd(RandUtil.generateRandomDate(2020, 2027));
            p.setBirthDate(RandUtil.generateRandomDate(1940, 1980));
        }

        return practitioners;
    }

    public List<PractitionerIdentity> getIdentity() throws DAOException {
        List<Practitioner> practitioners = get();
        List<PractitionerIdentity> ids = new ArrayList<PractitionerIdentity>();

        for(int i=0;i<practitioners.size();i++) {
            Practitioner practitioner = practitioners.get(i);
            PractitionerIdentity id = new PractitionerIdentity();
            id.setName(practitioner.getSourcesystemid());
            id.setSourceSystem("EMR1");
            id.setOrganization("American Medical Association");
            id.setSourceSystemId(practitioner.getSourcesystemid());
            id.setTypeCode("LIC");
            id.setTypeLabel("Licence");
            id.setValue("AMA-"+RandUtil.generateRandomInteger(1000,999999)+"-"+RandUtil.generateRandomInteger(1000,9999));
            id.setPractitionerId(practitioner.getSourcesystemid());
            id.setEffectiveStart(RandUtil.generateRandomDate(2011, 2017));
            id.setEffectiveEnd(RandUtil.generateRandomDate(2021, 2027));
            ids.add(id);
        }
        return ids;
    }

    public List<PractitionerQualification> getQualification() throws DAOException {
        List<Practitioner> practitioners = get();
        List<PractitionerQualification> qualifications = new ArrayList<PractitionerQualification>();

        MedicalschoolDAO dao = new MedicalschoolDAOImpl();
        List<Medicalschool> schools = dao.selectAll();

        for(int i=0;i<practitioners.size();i++) {
            int randSchoolRow = RandUtil.generateRandomInteger(0, schools.size()-1);
            Medicalschool school = schools.get(randSchoolRow);

            Practitioner practitioner = practitioners.get(i);
            PractitionerQualification qualification = new PractitionerQualification();
            qualification.setName(practitioner.getSourcesystemid());
            qualification.setSourceSystem("EMR1");
            qualification.setSourceSystemId(practitioner.getSourcesystemid());
            qualification.setPractitionerId(practitioner.getSourcesystemid());
            //qualification.setEffectiveStart(RandUtil.generateRandomDate(2011, 2017));
            qualification.setEffectiveEnd(RandUtil.generateRandomDate(1980, 2012));
            qualification.setIssue(school.getName());
            qualification.setTypeCode(school.getCertification());
            qualification.setTypeLabel(school.getCertification());
            qualifications.add(qualification);
        }
        return qualifications;
    }


    public List<PractitionerQualification> generateQualification() throws DAOException, FileNotFoundException, UnsupportedEncodingException {

        List<PractitionerQualification> qualifications = getQualification();
        System.out.println("Size:"+qualifications.size());

        StringBuffer buffer = new StringBuffer("");
        buffer.append("HC4a__SourceSystemId__c,HC4a__SourceSystem__c,HC4a__Issuer__c,HC4a__Code__c,HC4a__CodeLabel__c,HC4a__Practitioner__r:HC4A__SourceSystemId__C,HC4a__PeriodEnd__c").append("\n");

        for(int i=0;i<qualifications.size();i++) {
            PractitionerQualification p = qualifications.get(i);
            buffer.append(p.toCSV()).append("\n");

            if(i>5) {
                //break;
            }
        }

        System.out.println(buffer.toString());

        PrintWriter writer = new PrintWriter("/Users/sriram.gopalan/data/HC/data/PractitionerQualification.csv", "UTF-8");
        writer.println(buffer.toString());
        writer.close();

        return qualifications;
    }


    public List<PractitionerIdentity> generateIdentity() throws DAOException, FileNotFoundException, UnsupportedEncodingException {

        List<PractitionerIdentity> identities = getIdentity();
        System.out.println("Size:"+identities.size());

        StringBuffer buffer = new StringBuffer("");
        buffer.append("HC4a__SourceSystemId__c,HC4a__SourceSystem__c,Name,HC4a__Organization__c,HC4a__TypeCode__c,HC4a__TypeLabel__c,HC4a__Value__c,HC4a__EhrPractitioner__r:HC4A__SOURCESYSTEMID__C,HC4a__PeriodStartDate__c,HC4a__PeriodEndDate__c").append("\n");

        for(int i=0;i<identities.size();i++) {
            PractitionerIdentity p = identities.get(i);
            buffer.append(p.toCSV()).append("\n");
        }

        System.out.println(buffer.toString());

        PrintWriter writer = new PrintWriter("/Users/sriram.gopalan/data/HC/data/PractitionerIdenity.csv", "UTF-8");
        writer.println(buffer.toString());
        writer.close();

        return identities;
    }

    public List<Practitioner> generateRole() throws DAOException, FileNotFoundException, UnsupportedEncodingException {

        List<Practitioner> practitioners = get();
        System.out.println("Size:"+practitioners.size());

        StringBuffer buffer = new StringBuffer("");

        buffer.append("HC4a__SourceSystemId__c,HC4a__SourceSystem__c,HC4a__RoleLabel__c,HC4a__RoleCode__c,HC4a__Specialty1Label__c,HC4a__Specialty1Code__c,HC4a__Specialty2Label__c,HC4a__Specialty2Code__c,HC4a__Specialty3Label__c,HC4a__Specialty3Code__c,HC4a__EhrPractitioner__r:HC4A__SOURCESYSTEMID__C,HC4a__StartDate__c,HC4a__EndDate__c").append("\n");

        for(int i=0;i<practitioners.size();i++) {
            Practitioner p = practitioners.get(i);
            buffer.append(p.toRoleCSV()).append("\n");
            if(i>2) {
                //break;
            }
        }

        System.out.println(buffer.toString());

        PrintWriter writer = new PrintWriter("/Users/sriram.gopalan/data/HC/data/PractitionerRole.csv", "UTF-8");
        writer.println(buffer.toString());
        writer.close();

        return practitioners;
    }

    public List<Practitioner> generate() throws DAOException, FileNotFoundException, UnsupportedEncodingException {

        List<Practitioner> practitioners = get();
        System.out.println("Size:"+practitioners.size());

        StringBuffer buffer = new StringBuffer("");
        buffer.append("HC4a__SourceSystemId__c,HC4a__SourceSystem__c,HC4A__NAME__C,HC4A__GIVENNAME1__C,HC4A__FAMILYNAME1__C,HC4A__GENDER__C,HC4A__ADDRESS1LINE1__C,HC4A__ADDRESS1CITY__C,HC4A__ADDRESS1STATE__C,HC4A__ADDRESS1POSTALCODE__C,HC4A__ADDRESS1COUNTRY__C,HC4A__ADDRESS1STARTDATE__C,HC4A__ADDRESS1ENDDATE__C,HC4A__BIRTHDATE__C").append("\n");

        for(int i=0;i<practitioners.size();i++) {
            Practitioner p = practitioners.get(i);
            buffer.append(p.toCSV()).append("\n");
        }

        System.out.println(buffer.toString());

        PrintWriter writer = new PrintWriter("/Users/sriram.gopalan/data/HC/data/Practitioner.csv", "UTF-8");
        writer.println(buffer.toString());
        writer.close();

        return practitioners;
    }

    public static void main(String[] args) throws DAOException, FileNotFoundException, UnsupportedEncodingException {

        new PractitionerGenerator();
        //List<Practitioner> rows = generator.generate();
        //List<PractitionerIdentity> rows = generator.generateIdentity();
        //List<PractitionerQualification> rows = generator.generateQualification();
        //List<Practitioner> rows = generator.generateRole();


    }

}
