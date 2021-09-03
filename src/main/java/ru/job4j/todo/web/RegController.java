package ru.job4j.todo.web;

import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.HibernateUserRepository;
import ru.job4j.todo.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class RegController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        UserRepository repo = HibernateUserRepository.getInstance();
        Optional<User> user = repo.getByEmail(email);
        if (user.isPresent()) {
            req.setAttribute("errorMessage", "this email is already registered");
            req.getRequestDispatcher("/WEB-INF/jsp/reg.jsp").forward(req, resp);
        } else {
            repo.save(new User(req.getParameter("name"), email));
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        }
    }
}
