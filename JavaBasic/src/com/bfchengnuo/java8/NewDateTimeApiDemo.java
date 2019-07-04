package com.bfchengnuo.java8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.*;
import java.util.Locale;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

/**
 * J8 新赠日期相关 API 的使用，借鉴了不少 Joda-Time
 *
 * TemporalAdjusters 工具类常用方法：
 * see: https://github.com/bfchengnuo/MyRecord/blob/master/%E7%AC%94%E8%AE%B0/Java/Java8%2B/TemporalAdjuster%E7%B1%BB%E4%B8%AD%E7%9A%84%E5%B7%A5%E5%8E%82%E6%96%B9%E6%B3%95.png
 *
 * 通用方法一览：
 * see： https://github.com/bfchengnuo/MyRecord/blob/master/%E7%AC%94%E8%AE%B0/Java/Java8%2B/%E8%A1%A8%E7%A4%BA%E6%97%B6%E9%97%B4%E7%82%B9%E7%9A%84%E6%97%A5%E6%9C%9F-%E6%97%B6%E9%97%B4%E7%B1%BB%E7%9A%84%E9%80%9A%E7%94%A8%E6%96%B9%E6%B3%95.png
 *
 * @author Created by 冰封承諾Andy on 2019/6/25.
 */
public class NewDateTimeApiDemo {
    public static void main(String[] args) {
        // 基础使用
        baseUse();
        // 日期时间操作
        process();
        // 互相转换
        formatAndParse();
        // lambda 形式进行操作
        lambdaForm();
        // 自定义格式化器
        customizeFormatter();
    }

    /**
     * 基本使用
     * <p>
     * 日期： LocalDate
     * 时间： LocalTime
     * 日期时间： LocalDateTime
     * 格式化器： DateTimeFormatter （线程安全的）
     * <p>
     * 范围描述
     * <p>
     * Period 和 Duration 都是表示时间范围的
     * Period 基于日期值，而 Duration 基于时间值
     * <p>
     * 时间戳： Instant
     * <p>
     * 表示一个 EPOCH 时间戳（即以 0 表示 1970-01-01T00:00:00Z），精确到纳秒。
     * Instant 对象不包含时区信息，且值是不可变的。
     * Instant 对象包含两个值：秒数和纳秒数。其中秒数指的是 epoch 时间戳，而纳秒数指的是该秒内的纳秒时间。
     * <p>
     * see https://segmentfault.com/a/1190000013535651
     */
    @SuppressWarnings("AlibabaRemoveCommentedCode")
    private static void baseUse() {
        // 日期基本构造
        {
            LocalDate today = LocalDate.now();
            LocalDate date = LocalDate.of(2019, 6, 18);

            // 基本使用
            int year = date.getYear();
            Month month = date.getMonth();
            int day = date.getDayOfMonth();

            DayOfWeek dow = date.getDayOfWeek();
            int len = date.lengthOfMonth();
            boolean leap = date.isLeapYear();
            System.out.printf("年：%s, 月：%s, 日：%s, 周：%s, 月天数：%s, 是否闰年%s \n",
                    year, month.getValue(), day, dow.getValue(), len, leap);

            // 另一种形式
            date.get(ChronoField.YEAR);
            date.get(ChronoField.MONTH_OF_YEAR);
            date.get(ChronoField.DAY_OF_MONTH);

            // 字符串形式
            date = LocalDate.parse("2014-03-18");
        }


        // 时间的基本构造
        {
            LocalTime time = LocalTime.of(13, 45, 20);
            int hour = time.getHour();
            int minute = time.getMinute();
            int second = time.getSecond();

            // 字符串形式
            time = LocalTime.parse("13:45:20");
        }


        // 日期时间的合并
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);

        // 提取
        LocalDate date1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();

        // 获取当前时间戳
        Instant instant = Instant.now();
        System.out.println("day = " + instant);
        // Instant 对象的精确度比 System.currentTimeMillis() 高到不知道哪去了
        System.out.println("EpochSecond: " + instant.getEpochSecond());
        System.out.println("EpochMilli: " + instant.toEpochMilli());
        System.out.println("Nano: " + instant.getNano());

        // 自定义格式转换
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse("2019-11-11 00:00:00", formatter);
        System.out.println(dateTime.format(formatter));


        // 范围描述
        {
            Period tenDays = Period.between(
                    LocalDate.of(2014, 3, 8),
                    LocalDate.of(2014, 3, 18)
            );
            tenDays = Period.ofDays(10);
            Period threeWeeks = Period.ofWeeks(3);
            Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);

            // Duration.between(time1, time2);
            // Duration.between(dateTime1, dateTime2);
            // Duration.between(instant1, instant2);
            Duration threeMinutes = Duration.ofMinutes(3);
            threeMinutes = Duration.of(3, ChronoUnit.MINUTES);
        }
    }

    /**
     * 时间日期的操作处理
     *
     * 修改： withXXX (可以配合 TemporalAdjusters 工具类的方法达到指定特殊日期，例如本周末、下周一等)
     * 增加： plusXXX
     * 减少： minusXXX
     *
     */
    private static void process() {
        // 修改
        {
            // 2014-3-18
            LocalDate date1 = LocalDate.of(2014, 3, 18);
            // 2011-3-18
            LocalDate date2 = date1.withYear(2011);
            // 2011-3-25
            LocalDate date3 = date2.withDayOfMonth(25);
            // 2011-9-25
            LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);
        }

        // 操作
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        // 2014-03-25
        LocalDate date2 = date1.plusWeeks(1);
        // 2011-03-25
        LocalDate date3 = date2.minusYears(3);
        // 2011-09-25
        LocalDate date4 = date3.plus(6, ChronoUnit.MONTHS);


        // import static java.time.temporal.TemporalAdjusters.*;
        LocalDate d1 = LocalDate.of(2014, 3, 18);
        // 2014-03-23
        LocalDate d2 = date1.with(nextOrSame(DayOfWeek.SUNDAY));
        // 2014-03-31
        LocalDate d3 = date2.with(lastDayOfMonth());
    }

    /**
     * 时间日期的转换，主要通过 DateTimeFormatter 来自定义格式，它是线程安全的
     * 默认定义了几种常见格式，例如 yyyy-MM-dd
     */
    private static void formatAndParse() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        // 20140318
        System.out.println(date.format(DateTimeFormatter.BASIC_ISO_DATE));
        // 2014-03-18
        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));

        LocalDate date1 = LocalDate.parse("20140318", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse("2014-03-18", DateTimeFormatter.ISO_LOCAL_DATE);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse("2019-12-12 00:00:00", formatter);
        System.out.println(dateTime.format(formatter));
    }

    /**
     * Lambda 形式操作
     */
    @SuppressWarnings("Duplicates")
    private static void lambdaForm() {
        LocalDate date = LocalDate.now();

        // 获取下一个工作日，自动跳过周末
        date = date.with(temporal -> {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            } else if (dow == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));

        date = LocalDate.now();
        // 使用工具类
        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
                temporal -> {
                    DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
                    int dayToAdd = 1;
                    if (dow == DayOfWeek.FRIDAY) {
                        dayToAdd = 3;
                    }
                    if (dow == DayOfWeek.SATURDAY) {
                        dayToAdd = 2;
                    }
                    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
                });

        date = date.with(nextWorkingDay);
        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    /**
     * 自定义 Formatter ，了解
     */
    private static void customizeFormatter() {
        DateTimeFormatter italianFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                // 将解析样式更改为格式化程序的其余部分不区分大小写
                .parseCaseInsensitive()
                .toFormatter(Locale.CHINESE);

        System.out.println(LocalDateTime.now().format(italianFormatter));
    }


    /**
     * 定制的 TemporalAdjuster, 更简洁的参考 Lambda 写法
     * 获取下一个工作日，自动跳过周末
     */
    @SuppressWarnings("Duplicates")
    class NextWorkingDay implements TemporalAdjuster {
        @Override
        public Temporal adjustInto(Temporal temporal) {
            // 获取星期
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            // 周五增加 3 天
            if (dow == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            } else if (dow == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }

            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        }
    }
}
