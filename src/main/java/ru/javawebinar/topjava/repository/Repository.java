package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalTime;
import java.util.List;

public interface Repository {

    Meal save(Meal meal);

    boolean delete(Integer id);

    Meal get(Integer id);

    List<Meal> getAll();

    List<Meal> getAllBetweenHalfOpen (LocalTime startTime, LocalTime endTime);
}
