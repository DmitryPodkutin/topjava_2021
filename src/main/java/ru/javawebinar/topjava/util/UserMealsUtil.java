package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);
        System.out.println();
        filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000).forEach(System.out::println);
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> totalCalories = new HashMap<>();
        List<UserMeal> filteredUserMeals = new ArrayList<>();
        for (UserMeal meal : meals) {
            totalCalories.merge(meal.getDate(), meal.getCalories(), Integer::sum);
            if (isBetweenHalfOpen(startTime, endTime, meal)) {
                filteredUserMeals.add(meal);
            }
        }
        List<UserMealWithExcess> userMealWithExcesses = new ArrayList<>();
        for (UserMeal meal : filteredUserMeals) {
            userMealWithExcesses.add(createUserMealWithExcess(caloriesPerDay, totalCalories, meal));
        }
        return userMealWithExcesses;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> totalCalories = meals.stream()
                .collect(Collectors.groupingBy(UserMeal::getDate, Collectors.summingInt(UserMeal::getCalories)));
        return meals.stream()
                .filter(meal -> isBetweenHalfOpen(startTime, endTime, meal))
                .map(meal -> createUserMealWithExcess(caloriesPerDay, totalCalories, meal))
                .collect(Collectors.toList());
    }

    private static boolean isBetweenHalfOpen(LocalTime startTime, LocalTime endTime, UserMeal meal) {
        return TimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime);
    }

    private static UserMealWithExcess createUserMealWithExcess(int caloriesPerDay, Map<LocalDate, Integer> totalCalories, UserMeal meal) {
        return new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), totalCalories.get(meal.getDate()) > caloriesPerDay);
    }
}
