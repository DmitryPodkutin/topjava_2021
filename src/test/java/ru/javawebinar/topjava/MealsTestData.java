package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class MealsTestData {
    public static final int MEAL_ID = 100005;
    public static final int MEAL_ID_NOT_FOUND = 1;
    public static final Meal MEAL_1 = new Meal(MEAL_ID, LocalDateTime.of(2011, 1, 30, 0, 0, 0), "Еда на граничное значение", 100);
    public static final Meal MEAL_2 = new Meal(100002, LocalDateTime.of(2011, 1, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL_3 = new Meal(100003, LocalDateTime.of(2011, 1, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL_4 = new Meal(100004, LocalDateTime.of(2011, 1, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL_5 = new Meal(100006, LocalDateTime.of(2011, 1, 31, 10, 0), "Завтрак", 100);
    public static final Meal MEAL_6 = new Meal(100007, LocalDateTime.of(2011, 1, 31, 13, 0), "Обед", 500);
    public static final Meal MEAL_7 = new Meal(100008, LocalDateTime.of(2011, 1, 31, 20, 0), "Ужин", 410);

    public static final List<Meal> MEALS = Arrays.asList(MEAL_7, MEAL_6, MEAL_5, MEAL_4, MEAL_3, MEAL_2, MEAL_1);

    public static Meal getNew() {
        return new Meal(MEAL_ID, LocalDateTime.of(LocalDate.of(2011, 1, 28), LocalTime.of(11, 0, 0)), "New Eat", 1000);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(MEAL_ID,
                LocalDateTime.of(LocalDate.of(2011, 1, 30), LocalTime.of(0, 0, 0))
                , "Updated MEAL", 777);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparatorIgnoringFields().isEqualTo(expected);
    }
}
