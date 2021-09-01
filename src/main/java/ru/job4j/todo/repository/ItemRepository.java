package ru.job4j.todo.repository;

import ru.job4j.todo.model.Item;

import java.util.List;

public interface ItemRepository {
    Item save(Item ticket);

    List<Item> getAll();

    Item get(Integer id);
}
