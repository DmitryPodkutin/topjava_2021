package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MapRepository implements Repository {
    public Map<Integer, Meal> meals = new HashMap();
    private final AtomicInteger id = new AtomicInteger(0);

    @Override
    public void save(Meal meal) {
        meal.setId(id.incrementAndGet());
        meals.put(meal.getId(), meal);
    }

    @Override
    public void update(Meal meal) {
    meals.put(meal.getId(),meal);
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
        return new ArrayList<>(meals.values());
    }
}
