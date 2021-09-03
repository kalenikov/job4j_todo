package ru.job4j.todo.web;

import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.HibernateUserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        Optional<User> user = HibernateUserRepository.getInstance().getByEmail(email);
        if (user.isPresent()) {
            req.getSession().setAttribute("user", user.get());
            resp.sendRedirect(req.getContextPath() + "/index.do");
        } else {
            req.setAttribute("errorMessage", "Email not found");
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
        }
    }
}
