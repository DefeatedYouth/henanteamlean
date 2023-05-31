package com.team.common.util;

import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

    /**
     * @author: feng
     * @date: 2018/3/16
     * @description:
     */

public class DateUtil {

    private static final int DAY_MILLISECONDS = 1000 * 24 * 60 * 60;

    private static final int HOUR_MILLISECONDS = 1000 * 60 * 60;

    private static final int MINUTE_MILLISECONDS = 1000 * 60;

    //计算时间差，精确到天
    public static int diffDays(Date start, Date end) {
        long diff = end.getTime() - start.getTime();
        return (int) (diff / (DAY_MILLISECONDS));
    }
    //计算时间差，精确到小时
    public static int diffHours(Date start, Date end) {
        long diff = end.getTime() - start.getTime();
        return (int) (diff / (HOUR_MILLISECONDS));
    }
    //计算时间差，精确分钟
    public static int diffMinutes(Date start, Date end) {
        long diff = end.getTime() - start.getTime();
        return (int) (diff / (MINUTE_MILLISECONDS));
    }
    //计算时间差，精确到分钟
    public static int diffSeconds(Date start, Date end) {
        long diff = end.getTime() - start.getTime();
        return (int) (diff / 1000);
    }
    //获取当前时间，年-月-日
    public static Date getToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(new Date());
        Date date = null;
        try {
            date = sdf.parse(s);
        } catch (ParseException e) {

        }
        return date;
    }

        //获取当前时间，年-月-日 时:分:秒
        public static Date getDate() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = sdf.format(new Date());
            Date date = null;
            try {
                date = sdf.parse(s);
            } catch (ParseException e) {

            }
            return date;
        }

    /**
     * 获取前几天的时间
     *
     * @param date currentDate
     * @param days beforeDate
     * @return
     */
    public static Date getDateBefore(Date date, int days) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - days);
        return now.getTime();
    }

    /**
     * 获取后几天的时间
     *
     * @param date currentDate
     * @param days afterDays
     * @return
     */
    public static Date getDateAfter(Date date, int days) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + days);
        return now.getTime();
    }
    //转换Date  to  String
    public static String date2String(Date date,String str){
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        return sdf.format(date);
    }

    public static Date parseDate(String str) {
        return parseDate(str, "yyyy-MM-dd");
    }

    //转换String  to  Date,入参str:转换字符串，例如2018-01-01 00:00:00  pattern:转换后格式
    public static Date parseDate(String str, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static List<Date> getDatesBetween(Date startDate, Date endDate) {
        Long mils = (endDate.getTime() - startDate.getTime()) / (DAY_MILLISECONDS);
        int days = mils.intValue();
        List<Date> res = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            res.add(org.apache.commons.lang3.time.DateUtils.addDays(startDate, i));
        }
        return res;
    }

    public static List<Date> getDatesBetweenIncludeEnd(Date startDate, Date endDate){
        List<Date> res = getDatesBetween(startDate, endDate);
        res.add(endDate);
        return res;
    }

    public static Set<DayOfWeek> getWeekendDay(List<Integer> list) {
        Set<DayOfWeek> res = new HashSet<>();
        if (CollectionUtils.isEmpty(list)) {
            return res;
        }
        for (Integer i : list) {
            switch (i) {
                case 1:
                    res.add(DayOfWeek.MONDAY);
                    break;
                case 2:
                    res.add(DayOfWeek.TUESDAY);
                    break;
                case 3:
                    res.add(DayOfWeek.WEDNESDAY);
                    break;
                case 4:
                    res.add(DayOfWeek.THURSDAY);
                    break;
                case 5:
                    res.add(DayOfWeek.FRIDAY);
                    break;
                case 6:
                    res.add(DayOfWeek.SATURDAY);
                    break;
                case 7:
                    res.add(DayOfWeek.SUNDAY);
                    break;
                default:
            }
        }
        return res;
    }

    public static DayOfWeek getDayOfWeek(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDateTime.ofInstant(instant, zoneId).toLocalDate();
        return localDate.getDayOfWeek();
    }

    /**
     * 将 1,2,3 .... 形式的星期集合  转化为 星期一,星期二,星期三 .... 这种形式
     *
     * @param weekDays
     * @return
     */
    public static List<String> weekDaysChange(List<String> weekDays) {
        List<String> newWeekDays = new ArrayList<>();
        for (String weekDay : weekDays) {
            switch (weekDay) {
                case "1":
                    newWeekDays.add("星期一");
                    break;
                case "2":
                    newWeekDays.add("星期二");
                    break;
                case "3":
                    newWeekDays.add("星期三");
                    break;
                case "4":
                    newWeekDays.add("星期四");
                    break;
                case "5":
                    newWeekDays.add("星期五");
                    break;
                case "6":
                    newWeekDays.add("星期六");
                    break;
                case "7":
                    newWeekDays.add("星期日");
                    break;
                default:
            }
        }
        return newWeekDays;
    }

    public static DayOfWeek getDayOfWeekFromNumber(Integer day) {
        switch (day) {
            case 1:
                return DayOfWeek.MONDAY;
            case 2:
                return DayOfWeek.TUESDAY;
            case 3:
                return DayOfWeek.WEDNESDAY;
            case 4:
                return DayOfWeek.THURSDAY;
            case 5:
                return DayOfWeek.FRIDAY;
            case 6:
                return DayOfWeek.SATURDAY;
            case 7:
                return DayOfWeek.SUNDAY;
            default:
        }
        return DayOfWeek.SUNDAY;
    }

    public static boolean isSameDay(Date date1, Date date2) {
        return org.apache.commons.lang3.time.DateUtils.isSameDay(date1, date2);
    }

    public static Date addDays(Date date, int days){
        return org.apache.commons.lang3.time.DateUtils.addDays(date, days);
    }

    public static int compareTime(Date date1, Date date2){
        int     t1;
        int     t2;
        t1 = (int) (date1.getTime() % (24*60*60*1000L));
        t2 = (int) (date2.getTime() % (24*60*60*1000L));
        return (t1 - t2);
    }

    public static boolean isValidTonight(Date date1, Date date2){
        Instant instant1 = date1.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(instant1, zone);
        LocalTime localTime1 = localDateTime1.toLocalTime();

        Instant instant2 = date2.toInstant();
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant2, zone);
        LocalTime localTime2 = localDateTime2.toLocalTime();

        LocalTime now = LocalTime.now();

        if(localTime1.isBefore(localTime2) && now.isBefore(localTime2)){
            return true;
        }else if(localTime1.isAfter(localTime2) && (now.isAfter(localTime1)||now.isBefore(localTime2))){
            return true;
        }
        return false;
    }

        /**
         * 根据日期获取 星期
         *
         * @param datetime
         * @return
         */
        public static String dateToWeek(String datetime) {

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
            Calendar cal = Calendar.getInstance();
            Date date;
            try {
                date = f.parse(datetime);
                cal.setTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //一周的第几天
            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0)
                w = 0;
            return weekDays[w];
        }
}
