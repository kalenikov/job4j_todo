package ru.job4j.todo.lessons.manytomany.humans;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "cities")
@Getter
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public static City of(String name) {
        City city = new City();
        city.name = name;
        return city;
    }

    // getters, setters
    // equals() и hashCode()
}