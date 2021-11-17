package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.datajpa.DataJpaMealRepository;

import static ru.javawebinar.topjava.MealTestData.MEAL_MATCHER;
import static ru.javawebinar.topjava.MealTestData.meal1;
import static ru.javawebinar.topjava.UserTestData.USER_MATCHER;
import static ru.javawebinar.topjava.UserTestData.user;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaMealServiceTest extends AbstractMealServiceTest {
    @Autowired
    private DataJpaMealRepository repository;

    @Test
    public void getMealWithUser() {
        Meal actualMeal = repository.getWithUser(100002, 100000);
        User actualUser = actualMeal.getUser();
        actualUser.setMeals(null);
        MEAL_MATCHER.assertMatch(actualMeal, meal1);
        USER_MATCHER.assertMatch(actualMeal.getUser(), user);
    }
}
