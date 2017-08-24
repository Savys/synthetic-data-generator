package org.stg.core;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class RandUtil {

   // static SimpleDateFormat fmt = new SimpleDateFormat("YYYY-MM-dd");

    public static int getRandomNumberInRange(int min, int max) {

        if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static int generateRandomInteger(int min, int max) {
        return getRandomNumberInRange(min,max);
    }

    public static Boolean generateRandomBoolean() {
        return getRandomNumberInRange(0,1)==0?true:false;
    }

    public static Calendar generateRandomDate() {
        Calendar gc = new GregorianCalendar();
        int year = generateRandomInteger(1940, 2017);
        gc.set(Calendar.YEAR, year);
        int dayOfYear = generateRandomInteger(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
        return gc;
    }

    public static Calendar generateRandomDOB(int minYear,int maxYear) {
        Calendar gc = new GregorianCalendar();
        int year = generateRandomInteger(minYear, maxYear);
        gc.set(Calendar.YEAR, year);
        int dayOfYear = generateRandomInteger(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
        return gc;
    }

    public static String getFormattedDate(Calendar inDate) {
        return Consts.SFDC_DATEFMT.format(inDate.getTime());
    }

    public static String getFormattedDate(Date inDate) {
        return Consts.SFDC_DATEFMT.format(inDate);
    }

    public static Calendar generateRandomCalendarDate(int startYear, int startMonth, int EndYear, int EndMonth) {
        Calendar gc = Calendar.getInstance();
        int year = generateRandomInteger(startYear, EndYear);
        gc.set(Calendar.YEAR, year);
        int month = generateRandomInteger(startMonth, EndMonth);
        gc.set(Calendar.MONTH,month);

        int dayOfYear = generateRandomInteger(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
        return gc;
    }

    public static Date generateRandomDate(int startYear, int startMonth, int EndYear, int EndMonth) {
        Calendar gc = Calendar.getInstance();
        int year = generateRandomInteger(startYear, EndYear);
        gc.set(Calendar.YEAR, year);
        int month = generateRandomInteger(startMonth, EndMonth);
        gc.set(Calendar.MONTH,month);

        int dayOfYear = generateRandomInteger(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
        return gc.getTime();
    }

    public static Date getThreshHoldDate(int month) {
        Calendar gc = Calendar.getInstance();
        gc.setTime(new Date());
        gc.add(Calendar.MONTH, month);
        return gc.getTime();
    }


    public static String generateRandomDate(int startYear, int endYear) {
        GregorianCalendar gc = new GregorianCalendar();
        int year = generateRandomInteger(startYear, endYear);
        gc.set(Calendar.YEAR, year);
        int dayOfYear = generateRandomInteger(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
        String dateStr = gc.get(Calendar.YEAR) + "-" + (gc.get(Calendar.MONTH) + 1) + "-" + gc.get(Calendar.DAY_OF_MONTH);
        return dateStr;
    }

    public static String generateRandomPhone() {
        int areaCode = generateRandomInteger(101, 999);
        int localCode = generateRandomInteger(101, 999);
        int pNumber = generateRandomInteger(1001, 9999);

        return areaCode + "-" + localCode + "-" + pNumber;

    }

    public static Double generateRandomDouble(Double rangeMin, Double rangeMax) {
        Random r = new Random();
        Double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();

        return randomValue;
    }


}
