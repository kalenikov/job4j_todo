package ru.job4j.todo.lessons.post;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(schema = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @OneToMany
//    @JoinColumn(name = "post_id")
    private Set<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Category category;
//
//    @ManyToMany
//    @JoinTable(schema = "posts")
//    private Set<Icon> icons;
}
