package com.salesforce.industries.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class RandUtil {

    static SimpleDateFormat fmt = new SimpleDateFormat("YYYY-MM-dd");

    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static int generateRandomInteger(int min, int max) {
        return getRandomNumberInRange(min,max);
    }

    public static String generateRandomDate() {
        GregorianCalendar gc = new GregorianCalendar();
        int year = generateRandomInteger(1940, 2017);
        gc.set(Calendar.YEAR, year);
        int dayOfYear = generateRandomInteger(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        fmt.setCalendar(gc);
        String dateFormatted = fmt.format(gc.getTime());
        return dateFormatted;
    }

    public static String getFormattedDate(Date inDate) {
        return fmt.format(inDate);
    }

    public static Date generateRandomDate(int startYear, int startMonth, int EndYear, int EndMonth) {
        GregorianCalendar gc = new GregorianCalendar();
        int year = generateRandomInteger(startYear, EndYear);
        gc.set(Calendar.YEAR, year);
        int dayOfYear = generateRandomInteger(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        fmt.setCalendar(gc);
        return gc.getTime();
    }

    public static Date getThreshHoldDate(int month) {
        GregorianCalendar gc = new GregorianCalendar();
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


}
