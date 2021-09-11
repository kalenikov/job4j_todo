package ru.job4j.todo.lessons.hql;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "candidates")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
    private int id;
    private String name;
    private int experience;
    private int salary;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "db_id")
    private VacancyDB vacancyDB;

    public Candidate(String name, int experience, int salary, VacancyDB vacancyDB) {
        this.name = name;
        this.experience = experience;
        this.salary = salary;
        this.vacancyDB = vacancyDB;
    }
}