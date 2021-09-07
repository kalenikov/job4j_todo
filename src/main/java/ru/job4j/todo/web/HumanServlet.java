package ru.job4j.todo.web;

import ru.job4j.todo.lessons.manytomany.humans.City;
import ru.job4j.todo.lessons.manytomany.humans.HumanStore;
import ru.job4j.todo.lessons.manytomany.humans.Human;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HumanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<City> cities = HumanStore.instanceOf().allCities();
        req.setAttribute("allCities", cities);
        req.getRequestDispatcher("/WEB-INF/jsp/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] cIds = req.getParameterValues("cIds");
        String info = req.getParameter("info");
        Human human = Human.of(info);
        HumanStore.instanceOf().addNewHuman(human, cIds);
        resp.sendRedirect(req.getContextPath() + "/humans");
    }
}