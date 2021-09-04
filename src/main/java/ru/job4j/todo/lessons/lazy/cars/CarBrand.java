package ru.job4j.todo.lessons.lazy.cars;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@ToString
@Table(name = "car_brand")
public class CarBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "carBrand", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CarModel> models = new ArrayList<>();

    public CarBrand(String name) {
        this.name = name;
    }

    public List<CarModel> getModels() {
        return models;
    }
}
