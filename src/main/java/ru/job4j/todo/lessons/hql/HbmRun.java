package ru.job4j.todo.lessons.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;


public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            VacancyDB db = new VacancyDB("db1", List.of(
                    new Vacancy("vacancy1"),
                    new Vacancy("vacancy2"),
                    new Vacancy("vacancy3")
            ));

            session.persist(new Candidate("cand1", 10, 10, db));
            session.persist(new Candidate("cand2", 20, 20, db));
            session.persist(new Candidate("cand3", 30, 30, db));

            List<Candidate> candidates = session.createQuery(
                            "select distinct c from Candidate c " +
                                    "join fetch c.vacancyDB db " +
                                    "join fetch db.vacancies",
                            Candidate.class)
                    .getResultList();
            for (Candidate candidate : candidates) {
                System.out.println(candidate);
            }

            candidates = session.createQuery("from Candidate s where s.id = 1", Candidate.class)
                    .getResultList();

            Candidate candidate = session.createQuery("from Candidate s where s.name = :name", Candidate.class)
                    .setParameter("name", "cand2")
                    .getSingleResult();

            session.createQuery("update Candidate s set s.salary = s.salary * 1.5 ")
                    .executeUpdate();

            session.createQuery("delete from Candidate where id = :id")
                    .setParameter("id", 1)
                    .executeUpdate();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}