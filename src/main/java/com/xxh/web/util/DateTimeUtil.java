package com.xxh.web.util;

import java.time.LocalDate;

/**
 * @author 小小黑
 */
public class DateTimeUtil {

    public static String getDateNow() {
        LocalDate todayDate = LocalDate.now();
        return todayDate.toString();
    }

    public static void main(String[] args) {
        System.out.println("今天的日期："+getDateNow());
    }
}
