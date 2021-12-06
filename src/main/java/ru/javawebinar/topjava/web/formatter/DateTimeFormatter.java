package ru.javawebinar.topjava.web.formatter;

import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

public class DateTimeFormatter {
    private static final java.time.format.DateTimeFormatter TIME_FORMATTER = java.time.format.DateTimeFormatter.ofPattern("HH:mm");
    private static final java.time.format.DateTimeFormatter DATE_FORMATTER = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy");


    public static final class DateFormatter implements Formatter<LocalDate> {

        @Override
        public String print(LocalDate date, Locale locale) {
            return date.format(DATE_FORMATTER);
        }

        @Override
        public LocalDate parse(String date, Locale locale) {
            return date.length() != 0 ? LocalDate.parse(date, DATE_FORMATTER) : null;
        }
    }

    public static final class TimeFormatter implements Formatter<LocalTime> {

        public String print(LocalTime time, Locale locale) {
            return time.format(TIME_FORMATTER);
        }

        public LocalTime parse(String time, Locale locale) {
            return time.length() != 0 ? LocalTime.parse(time, TIME_FORMATTER) : null;
        }
    }
}
