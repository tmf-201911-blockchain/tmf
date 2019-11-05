package com.whalecloud.util;

import com.google.common.collect.Maps;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * @author zhaoyanac
 * @date 2019/10/17
 */
public class DateUtils {
    public static final String DateFormatString = "yyyy-MM-dd HH:mm:ss";
    public static final String DateFormatString_17 = "yyyyMMddHHmmssSSS";
    public static final String DateFormatString_18 = "dd/MM/yyyy";
    public static final String DateFormatString_19 = "HH:MM:SS DD/MM/YYYY";


    private static Map<String, SimpleDateFormat> simpleDateFormatMap = Maps.newHashMap();

    public DateUtils() {
    }

    public static String getCurrentDateString() {
        return getString(new Date());
    }

    public static String getCurrentDateString(String dateFormat) {
        DateTime dateTime = new DateTime();
        return dateTime.toString(dateFormat);
    }

    public static Date getDate(String dateString) throws Exception {
        return getDate(dateString, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date getDate(String dateString, String dateformat) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(dateformat);
        return format.parse(dateString);
    }

    public static String getString(Date date) {
        return getString(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getString(Date date, String DateFormatString) {
        if (simpleDateFormatMap.containsKey(DateFormatString)) {
            return ((SimpleDateFormat)simpleDateFormatMap.get(DateFormatString)).format(date);
        } else {
            SimpleDateFormat format = new SimpleDateFormat(DateFormatString);
            simpleDateFormatMap.put(DateFormatString, format);
            return format.format(date);
        }
    }

    public static String formatDateTime(DateTime dateTime, String style, String lang) {
        DateTimeFormatter formatter = DateTimeFormat.forStyle(style).withLocale(new Locale(lang));
        return dateTime.toString(formatter);
    }

    public static Date minusTime(Date date, DateUtils.TimePart timePart, int time) {
        DateTime dateTime = new DateTime(date);
        switch(timePart) {
            case Year:
                return dateTime.minusYears(time).toDate();
            case Month:
                return dateTime.minusMonths(time).toDate();
            case Day:
                return dateTime.minusDays(time).toDate();
            case Hour:
                return dateTime.minusHours(time).toDate();
            case Minute:
                return dateTime.minusMinutes(time).toDate();
            case Second:
                return dateTime.minusSeconds(time).toDate();
            default:
                return date;
        }
    }

    public static Date plusTime(Date date, DateUtils.TimePart timePart, int time) {
        DateTime dateTime = new DateTime(date);
        switch(timePart) {
            case Year:
                return dateTime.plusYears(time).toDate();
            case Month:
                return dateTime.plusMonths(time).toDate();
            case Day:
                return dateTime.plusDays(time).toDate();
            case Hour:
                return dateTime.plusHours(time).toDate();
            case Minute:
                return dateTime.plusMinutes(time).toDate();
            case Second:
                return dateTime.plusSeconds(time).toDate();
            default:
                return date;
        }
    }

    public static Date getDateAddHours(Date curDate, Integer hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        calendar.set(11, calendar.get(11) + hours);
        return calendar.getTime();
    }

    public static Date getDateAddMinutes(Date curDate, Integer minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        calendar.set(12, calendar.get(12) + minutes);
        return calendar.getTime();
    }

    public static Integer getDatePart(Date curDate, int datePart) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        return cal.get(datePart);
    }

    public static boolean equals(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return true;
        } else if (date1 != null && date2 != null) {
            return date1.compareTo(date2) == 0;
        } else {
            return false;
        }
    }

    public static int daysBetween(Date smdate, Date bdate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / 86400000L;
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static enum TimePart {
        Year,
        Month,
        Day,
        Hour,
        Minute,
        Second;

        private TimePart() {
        }
    }

}


