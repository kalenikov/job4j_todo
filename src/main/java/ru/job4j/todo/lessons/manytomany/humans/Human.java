package ru.job4j.todo.lessons.manytomany.humans;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "humans")
public class Human {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String info;

    private Calendar created;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<City> cities = new ArrayList<>();

    public void addCity(City city) {
        this.cities.add(city);
    }

    public static Human of(String info) {
        Human human = new Human();
        human.info = info;
        human.created = Calendar.getInstance();
        return human;
    }

    // getters, setters
    // equals() и hashCode()
}