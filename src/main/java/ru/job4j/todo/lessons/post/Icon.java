package ru.job4j.todo.lessons.post;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(schema = "posts")
@ToString
public class Icon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToMany(mappedBy = "icons")
    private Set<Post> posts;
}
