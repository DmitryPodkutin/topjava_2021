package ru.javawebinar.topjava.repository.jdbc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealsTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealsTestData.MEAL_ID;
import static ru.javawebinar.topjava.MealsTestData.MEAL_ID_NOT_FOUND;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID_NOT_FOUND;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JdbcMealRepositoryTest {
    @Autowired
    JdbcMealRepository repository;

    @Test
    public void save() {
        Meal created = repository.save(MealsTestData.getNew(), USER_ID);
        Integer newId = created.getId();
        Meal newMeal = MealsTestData.getNew();
        newMeal.setId(newId);
        MealsTestData.assertMatch(created, newMeal);
        MealsTestData.assertMatch(repository.get(newId, USER_ID), newMeal);
    }

    @Test
    public void delete() {
        Assert.assertTrue(repository.delete(MEAL_ID, USER_ID));
    }

    @Test
    public void deletedNotFound() {
        Assert.assertFalse(repository.delete(MEAL_ID_NOT_FOUND,USER_ID));
    }

    @Test
    public void deleteDoNotBelongUser() {
        Assert.assertNull(repository.get(MEAL_ID, USER_ID_NOT_FOUND));
    }

    @Test
    public void get() {
        Meal expected = MealsTestData.MEAL;
        MealsTestData.assertMatch(repository.get(expected.getId(), UserTestData.USER_ID),expected);
    }

    @Test
    public void getNotFound() {
        Assert.assertNull(repository.get(MEAL_ID_NOT_FOUND, UserTestData.USER_ID));
    }

    @Test
    public void getDoNotBelongUser() {
        Assert.assertNull(repository.get(MEAL_ID, USER_ID_NOT_FOUND));
    }

    @Test
    public void getAll() {
        List<Meal> meals = repository.getAll(100000);
        MealsTestData.assertMatch(meals, MealsTestData.MEALS);
    }

    @Test
    public void getBetweenHalfOpen() {
        List<Meal> meals = repository.getBetweenHalfOpen(LocalDateTime.of(2011, 1, 31,10,0), LocalDateTime.of(2011, 1, 31,20,0), 100000);
        MealsTestData.assertMatch(meals, MealsTestData.MEALS.get(0), MealsTestData.MEALS.get(1), MealsTestData.MEALS.get(2));
    }
}