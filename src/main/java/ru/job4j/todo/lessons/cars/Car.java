package ru.job4j.todo.lessons.cars;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "car_drivers",
            joinColumns = {
                    @JoinColumn(name = "driver_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "car_id", nullable = false, updatable = false)})
    private List<Person> drivers;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Person owner;
}
