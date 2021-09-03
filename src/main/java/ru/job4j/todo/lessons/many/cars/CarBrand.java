package ru.job4j.todo.lessons.many.cars;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "car_brand")
public class CarBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CarModel> models = new ArrayList<>();

    public CarBrand(String name) {
        this.name = name;
    }

    public List<CarModel> getModels() {
        return models;
    }
}
