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
import java.util.List;

public class MealServlet extends HttpServlet {
    public static final String EDIT_OR_SAVE = "/edit.jsp";
    private final Repository repository = new MapRepository();
    private static final Logger log = getLogger(MealServlet.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime").trim(), TimeUtil.DATE_TIME_FORMATTER);
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        Meal meal = new Meal(dateTime, description, calories);

        if (id == null || id.isEmpty()) {
            log.info("Meal is saved ");
        } else {
            log.info("Meal is updated");
            meal.setId(Integer.valueOf(id));
        }
        repository.save(meal);

        request.setAttribute("meals", getMealsTo());
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "/meals.jsp";
        String action = request.getParameter("action");
        String idParameter = request.getParameter("id");
        if (action != null) {
            switch (action) {
                case "save":
                    forward = EDIT_OR_SAVE;
                    break;
                case "edit":
                    forward = EDIT_OR_SAVE;
                    request.setAttribute("meal", repository.get(Integer.parseInt(idParameter)));
                    break;
                case "delete":
                    log.info("Meal is deleted");
                    repository.delete(Integer.parseInt(idParameter));
                    response.sendRedirect("meals");
                    return;
                default:
                    request.setAttribute("meals", getMealsTo());
                    break;
            }
        }
        request.setAttribute("meals", getMealsTo());
        request.getRequestDispatcher(forward).forward(request, response);
    }

    private List<MealTo> getMealsTo() {
        return MealsUtil.getFilteredTos(repository.getAll(),MealsUtil.CALORIES_PER_DAY, LocalTime.MIN,LocalTime.MAX);
    }
}
