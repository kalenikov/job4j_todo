package ru.job4j.todo.lessons.cars;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @ManyToMany(mappedBy = "drivers")
    private List<Car> cars;
}
