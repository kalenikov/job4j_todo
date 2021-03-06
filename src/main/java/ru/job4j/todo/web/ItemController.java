package ru.job4j.todo.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.ItemsDto;
import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.HibernateCategoryRepository;
import ru.job4j.todo.service.ItemService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;


public class ItemController extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        ItemsDto dto = new ItemsDto(
                ItemService.getInstance().getAll(),
                HibernateCategoryRepository.getInstance().getAll()
        );
        try (PrintWriter writer = resp.getWriter()) {
            writer.print(GSON.toJson(dto));
            writer.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.addHeader("Access-Control-Allow-Origin", "*");

        // manual extract post request body
        String body = req.getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));

        // generate dto item from json body
        Item dto = GSON.fromJson(body, Item.class);
        dto.setUser((User) req.getSession().getAttribute("user"));
        Item item = ItemService.getInstance().save(dto);

        try (PrintWriter writer = resp.getWriter()) {
            writer.print(GSON.toJson(item));
            writer.flush();
        }
    }
}
