package com.bfchengnuo.basic;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

/**
 * GMT 时区相关
 * @author 冰封承諾Andy
 * @date 2021/12/4
 */
public class GMTUtil {
    public static void main(String[] args) {
        String timeZone = "GMT+7";
        final ZoneId zoneId = TimeZone.getTimeZone(timeZone).toZoneId();

        LocalDateTime localDateTime = LocalDateTime.now(zoneId);
        // Hutool 提供的日期偏移计算
        // localDateTime = LocalDateTimeUtil.offset(localDateTime, 10, ChronoUnit.MILLIS);

        // 将 GMT+7 的时间转换为系统时区的时间
        Instant localDateTime2Instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date d = Date.from(localDateTime2Instant);
    }


    private static Long dateTransformBetweenTimeZone(Date date, String timeZone){
        // TimeZone srcTimeZone = TimeZone.getTimeZone(ZoneId.systemDefault());
        TimeZone srcTimeZone = TimeZone.getDefault();
        TimeZone destTimeZone = TimeZone.getTimeZone(timeZone);
        return dateTransformBetweenTimeZone(date, srcTimeZone, destTimeZone);
    }

    private static Long dateTransformBetweenTimeZone(Date sourceDate, TimeZone sourceTimeZone, TimeZone targetTimeZone) {
        return sourceDate.getTime() - sourceTimeZone.getRawOffset() + targetTimeZone.getRawOffset();
    }
}
