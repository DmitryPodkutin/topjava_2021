package ru.javawebinar.topjava.service;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.MEAL_MATCHER;
import static ru.javawebinar.topjava.MealTestData.meals;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest{
    @Autowired
    private UserService service;

    @Test
    public void getUserWithMeals() {
        User actualUser = service.get(USER_ID);
        List<Meal> actualMealList =new ArrayList<>(actualUser.getMeals());
        actualMealList.sort(Comparator.comparing(Meal::getDateTime).reversed());
        USER_MATCHER.assertMatch(actualUser, user);
        MEAL_MATCHER.assertMatch(actualMealList, meals);
    }
}
