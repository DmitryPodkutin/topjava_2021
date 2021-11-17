package ru.javawebinar.topjava.service;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.datajpa.DataJpaUserRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.MEAL_MATCHER;
import static ru.javawebinar.topjava.MealTestData.meals;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest{

    @Autowired
    private DataJpaUserRepository repository;

    @Test
    public void getUserWithMeals() {
        User actualUser = repository.getWithMeals(USER_ID);
        System.out.println(actualUser);
        List<Meal> actualMealList =new ArrayList<>(actualUser.getMeals());
        actualMealList.sort(Comparator.comparing(Meal::getDateTime).reversed());
        USER_MATCHER.assertMatch(actualUser, user);
        MEAL_MATCHER.assertMatch(actualMealList, meals);
    }
}
