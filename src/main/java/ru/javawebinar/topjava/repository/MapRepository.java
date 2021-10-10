package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MapRepository implements Repository {
    public Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.getId() == null) {
            meal.setId(id.incrementAndGet());
        }
        meals.put(meal.getId(), meal);
        return meals.get(meal.getId());
    }

    @Override
    public boolean delete(Integer id) {
        return meals.remove(id) != null;
    }

    @Override
    public Meal get(Integer id) {
        return meals.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return getAllByFilter(meal -> true);
    }

    @Override
    public List<Meal> getAllBetweenHalfOpen(LocalTime startTime, LocalTime endTime) {
        return getAllByFilter(meal -> TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime));
    }

    public List<Meal> getAllByFilter(Predicate<Meal> filter) {
        return meals.values().stream()
                .filter(filter)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}
