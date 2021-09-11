package ru.job4j.todo.lessons.hql.ex;

import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "books")
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String publishingHouse;
}