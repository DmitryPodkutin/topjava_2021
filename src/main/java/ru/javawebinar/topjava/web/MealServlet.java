package ru.javawebinar.topjava.web;

import static org.slf4j.LoggerFactory.getLogger;
import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MapRepository;
import ru.javawebinar.topjava.repository.Repository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

public class MealServlet extends HttpServlet {
    private final Repository repository = new MapRepository();
    private static final Logger log = getLogger(MealServlet.class);

    public void init() {
        repository.save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        repository.save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        repository.save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        repository.save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        repository.save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        repository.save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        repository.save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime").trim(), TimeUtil.DATE_TIME_FORMATTER);
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        Meal meal = new Meal(dateTime, description, calories);
        if (id == null || id.isEmpty()) {
            log.info("Meal is saved ");
            repository.save(meal);
        } else {
            log.info("Meal is updated");
            meal.setId(Integer.valueOf(id));
            repository.update(meal);
        }

        request.setAttribute("meals", getMealsTo());
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String idParameter = request.getParameter("id");
        if (action != null && idParameter != null) {
            int id = Integer.parseInt(idParameter);
            switch (action) {
                case "delete":
                    log.info("Meal is deleted");
                    repository.delete(id);
                    response.sendRedirect("meals");
                    return;
                case "edit":
                    request.setAttribute("meal", repository.get(id));
                    break;
                default:
                    request.setAttribute("meals", getMealsTo());
                    break;
            }
        }
            request.setAttribute("meals", getMealsTo());
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    private List<MealTo> getMealsTo() {
        return MealsUtil.filteredByStreams(repository.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
    }
}
