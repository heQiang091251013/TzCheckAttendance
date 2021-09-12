package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws ParseException {
        System.out.println("1111");
        String date = "2021/09/07";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date parse = sdf.parse(date);
        System.out.println(date.toString());
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String week = dateFm.format(parse);
        System.out.println(week);
    }
}
