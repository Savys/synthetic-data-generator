package com.salesforce.industries.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.salesforce.industries.persistence.model.UsPostalCode;

public class PostalCodeUtil {

    List<UsPostalCode> codes = new ArrayList<UsPostalCode>();

    public PostalCodeUtil() throws IOException {
        codes = readFile();
    }

    public static int randInt(int min, int max) {
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return randomNum;
    }
    public UsPostalCode get() throws IOException {
        int rand = randInt(0,codes.size()-1);
        return codes.get(rand);
    }

    public static List<UsPostalCode> readFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/Users/sriram.gopalan/Downloads/free-zipcode-database-Primary.csv"));
        String line = null;
        List<UsPostalCode> codes = new ArrayList<UsPostalCode>();
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            int f=0;
            UsPostalCode postalCode = new UsPostalCode();
            for (String str : values) {
                if(f==0) {
                    postalCode.code =str;
                }else if(f==1) {
                    postalCode.city =str;
                }else if(f==2) {
                    postalCode.state = str;
                }
                f++;
            }
            codes.add(postalCode);
        }
        br.close();
        return codes;
    }

    public static void main(String[] args) throws IOException {
        PostalCodeUtil util = new PostalCodeUtil();
        util.get();
    }

}
