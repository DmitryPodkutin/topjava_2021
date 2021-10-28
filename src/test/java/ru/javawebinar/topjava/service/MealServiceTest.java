package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealsTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealsTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;
import static ru.javawebinar.topjava.UserTestData.assertMatch;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    MealService service;

    @Test
    public void get() {
        Meal meal = service.get(MEAL.getId(), USER_ID);
        MealsTestData.assertMatch(meal, MEAL);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(MEAL_ID_NOT_FOUND,USER_ID));
    }

    @Test
    public void delete() {
        service.delete(MEAL_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(MEAL_ID, USER_ID));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(MEAL_ID_NOT_FOUND,USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> meals = service.getBetweenInclusive(LocalDate.of(2011, 1, 31), LocalDate.of(2011, 1, 31), 100000);
        MealsTestData.assertMatch(meals, MEALS.get(0), MEALS.get(1), MEALS.get(2));
    }

    @Test
    public void getAll() {
        List<Meal> meals = service.getAll(100000);
        MealsTestData.assertMatch(meals, MEALS);
    }

    @Test
    public void update() {
        Meal updated = MealsTestData.getUpdated();
        service.update(updated, USER_ID);
        MealsTestData.assertMatch(service.get(updated.getId(), USER_ID), MealsTestData.getUpdated());
    }

    @Test
    public void updateNotFound() {
        Meal updated = MealsTestData.getUpdated();
        assertThrows(NotFoundException.class, () -> service.update(updated,100003));
    }

    @Test
    public void create() {
        Meal created = service.create(MealsTestData.getNew(), USER_ID);
        Integer newId = created.getId();
        Meal newMeal = MealsTestData.getNew();
        newMeal.setId(newId);
        MealsTestData.assertMatch(created, newMeal);
        MealsTestData.assertMatch(service.get(newId, USER_ID), newMeal);
    }

    @Test
    public void duplicateDateCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create( new Meal( LocalDateTime.of(2011, 1, 30, 10, 0), "Duplicate", 100), USER_ID));
    }
}