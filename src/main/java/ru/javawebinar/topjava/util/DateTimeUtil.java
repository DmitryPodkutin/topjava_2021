package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate ifNullSetMinDate(LocalDate date) {
        return date == null ? LocalDate.MIN : date;
    }

    public static LocalDate ifNullSetMaxDate(LocalDate date) {
        return date == null ? LocalDate.MAX : date;
    }

    public static LocalTime ifNullSetMinTime(LocalTime time) {
        return time == null ? LocalTime.MIN : time;
    }

    public static LocalTime ifNullSetMaxTime(LocalTime time) {
        return time == null ? LocalTime.MAX : time;
    }
}

