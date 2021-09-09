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
}