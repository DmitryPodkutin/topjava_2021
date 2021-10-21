package ru.javawebinar.topjava.util;

public class FilterUtil {
    public static <T extends Comparable<T>> boolean filter(T startTime, T value, T endTime) {
        return value == null || value.compareTo(startTime) >= 0 && value.compareTo(endTime) < 0;
    }
}
