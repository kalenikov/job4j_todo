package ru.job4j.todo.lessons.cars;

import javax.persistence.*;

@Entity
@Table(name = "engine")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
}
