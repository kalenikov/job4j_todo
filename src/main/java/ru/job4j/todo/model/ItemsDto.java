package ru.job4j.todo.model;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ItemsDto {
    private List<Item> items;
    private List<Category> categories;
}
