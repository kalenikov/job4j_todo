package ru.job4j.todo.lessons.manytomany.books;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todo.lessons.manytomany.Person;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Author author1 = new Author("author1");
            Author author2 = new Author("author2");

            Book book1 = new Book("book1");
            Book book2 = new Book("book2");
            Book book3 = new Book("book3");

            author1.getBooks().add(book1);
            author1.getBooks().add(book2);
            author2.getBooks().add(book2);
            author2.getBooks().add(book3);

            session.persist(author1);
            session.persist(author2);

            Author author = session.get(Author.class, 1);
            session.remove(author);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
