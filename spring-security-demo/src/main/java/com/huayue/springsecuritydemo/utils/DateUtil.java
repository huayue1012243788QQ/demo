package com.huayue.springsecuritydemo.utils;

import java.util.Date;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/1/23.
 */
public class DateUtil {
    public static long subDate(Date start,Date end) {
        return (end.getTime() - start.getTime()) / (24 * 60 * 60 * 1000);
    }
    public static void main(String[] args) {
        Date date = new Date();
        long s = 1548231407532L;
        System.out.println(s/ (24 * 60 * 60 * 1000 * 365));
    }
}
