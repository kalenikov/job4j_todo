package ru.job4j.todo.lessons.hql.ex;

import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "students")
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private String city;
    @OneToOne(fetch = FetchType.LAZY)
    private Account account;
}