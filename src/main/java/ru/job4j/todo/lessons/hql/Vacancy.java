package ru.job4j.todo.lessons.hql;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "vacancies")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public Vacancy(String name) {
        this.name = name;
    }
}
