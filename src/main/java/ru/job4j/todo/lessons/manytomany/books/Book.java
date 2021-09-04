package ru.job4j.todo.lessons.manytomany.books;

import lombok.NoArgsConstructor;
import ru.job4j.todo.lessons.manytomany.books.Author;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public Book(String name) {
        this.name = name;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Author> authors;

    public List<Author> getAuthors() {
        return authors;
    }
}
