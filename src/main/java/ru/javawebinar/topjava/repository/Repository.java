package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface Repository {

    void save(Meal meal);

    void update(Meal meal);

    boolean delete(Integer id);

    Meal get(Integer id);

    List<Meal> getAll();
}
