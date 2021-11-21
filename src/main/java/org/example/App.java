package org.example;

import com.alibaba.fastjson.JSONArray;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Hello world!
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        System.out.println( "自动提交考勤启动成功!" );
        //登录
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://kq.topcheer.xyz:8092/Attendance/login");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
        parameters.add(new BasicNameValuePair("u_account","he.qiang"));
        parameters.add(new BasicNameValuePair("u_password","111111"));
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters,"UTF-8");
        httpPost.setEntity(formEntity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            String loginResult = EntityUtils.toString(entity, "utf-8");
            if (loginResult.contains("考勤管理")) {
                System.out.println("登录成功");
            }
        }
        response.close();

        //查找未提交的考勤
        HttpGet httpGet = new HttpGet("http://kq.topcheer.xyz:8092/Attendance/attendance/check");
        CloseableHttpResponse checkResponse = httpClient.execute(httpGet);
        if (checkResponse.getStatusLine().getStatusCode() == 200) {
            String loginResult = EntityUtils.toString(checkResponse.getEntity(), "utf-8");
            List<String> list = JSONArray.parseArray(loginResult, String.class);
            for (String s : list) {
                String[] split = s.split(":");
                String date = split[0];
                String week = getWeek(date);
                add(date,week,httpClient);
            }

        }

        httpClient.close();
    }


    /**
     * 添加考勤
     * @param date
     * @param week
     */
    public static void add(String date, String week, CloseableHttpClient httpClient) throws IOException {

        HttpPost httpPost = new HttpPost("http://kq.topcheer.xyz:8092/Attendance/attendance/add");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(10);
        parameters.add(new BasicNameValuePair("a_date",date));
        parameters.add(new BasicNameValuePair("a_week",week));
        if(!(week.equals(weeks[0]) || week.equals(weeks[6]))){
            parameters.add(new BasicNameValuePair("a_calendar","工作日"));
            parameters.add(new BasicNameValuePair("a_workTime","8"));
            parameters.add(new BasicNameValuePair("a_overTime",""));
            parameters.add(new BasicNameValuePair("a_isCost","0"));
            parameters.add(new BasicNameValuePair("a_describe","咨询服务提升功能开发"));
            parameters.add(new BasicNameValuePair("design",""));
            parameters.add(new BasicNameValuePair("TRserial","TR0001"));
            parameters.add(new BasicNameValuePair("XMserial","XM0203"));
            parameters.add(new BasicNameValuePair("KHserial","KH0002"));
        }
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters,"UTF-8");
        httpPost.setEntity(formEntity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            String addResult = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            System.out.println(addResult);
        }
        response.close();

    }

    static String[]  weeks = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    /**
     * 根据日期取得星期几
     * @param date
     * @throws ParseException
     */
    @Test
    public static String getWeek(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date parse = sdf.parse(date);
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String week = dateFm.format(parse);
        return week;
    }




}
