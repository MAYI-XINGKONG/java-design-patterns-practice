package com.niudada.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 时间工具类
 */
public class DateUtil {

    /**
     * 获取当前日期加上指定天数后的日期
     *
     * @param d 需要添加的天数，可以是负数
     * @return 格式化后的日期字符串，格式为 'YYYY-MM-DD'
     */
    public static String getFutureDate(int d) {
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();

        // 加上指定的天数
        LocalDate futureDate = currentDate.plusDays(d);

        // 格式化为 'YYYY-MM-DD'
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return futureDate.format(formatter);
    }
}
