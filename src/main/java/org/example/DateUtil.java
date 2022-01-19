package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    public static  final  String DATE_SLASH= "yyyy/MM/dd";
    public static  final  String DATE_VERTICAL = "yyyy-MM-dd";

    /**
     * 获取当月剩余天数
     * @return
     */
    public static List<String> getCurrentMonthRemainDays() {
        List<String> list = new ArrayList<String>();
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int year = aCalendar.get(Calendar.YEAR);//年份
        int month = aCalendar.get(Calendar.MONTH) + 1;//月份
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        int dateStart = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + 1; //剩余开始天数
        for (int i = dateStart; i <= day; i++) {
            String datStr = (i < 10) ? "0" + i : String.valueOf(i);
            String monthStr = (month < 10) ? "0" + month : String.valueOf(month);
            list.add(year + "/" + monthStr + "/" + datStr);
        }
        return list;
    }


    static String[]  weeks = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    /**
     * 根据日期取得星期几
     * @param date
     * @throws ParseException
     */

    public static String getWeek(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date parse = sdf.parse(date);
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String week = dateFm.format(parse);
        return week;
    }
}
