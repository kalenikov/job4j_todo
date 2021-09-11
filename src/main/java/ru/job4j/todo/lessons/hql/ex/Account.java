package ru.job4j.todo.lessons.hql.ex;

import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private boolean active;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        this.books.add(book);
    }
}