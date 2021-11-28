package org.example;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Test {
    public static void main(String[] args) throws ParseException {


        List<String> list = new ArrayList<>();
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int year = aCalendar.get(Calendar.YEAR);//年份
        int month = aCalendar.get(Calendar.MONTH) + 1;//月份
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        int dateStart = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+1;
        for (int i = dateStart; i <= day; i++) {
            String aDate = year +"/"+month+"/"+i;
            list.add(aDate);
        }




    }
}
