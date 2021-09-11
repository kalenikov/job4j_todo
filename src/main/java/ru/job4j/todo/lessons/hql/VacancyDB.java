package ru.job4j.todo.lessons.hql;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vacancy_db")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class VacancyDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "db_id")
    private List<Vacancy> vacancies;

    public VacancyDB(String name, List<Vacancy> vacancies) {
        this.name = name;
        this.vacancies = vacancies;
    }
}
