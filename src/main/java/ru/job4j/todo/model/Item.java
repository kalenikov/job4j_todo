package ru.job4j.todo.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description;
    private Timestamp created = Timestamp.valueOf(LocalDateTime.now());
    private boolean done = false;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

   public Integer getId() {
        return id;
    }

    public Item() {
    }

    public Item(String description) {
        this.description = description;
    }

    public Item(Integer id, String description, Boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", done=" + done +
                ", user=" + user +
                '}';
    }
}
