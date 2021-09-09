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

            session.save(new Candidate(0, "cand1", 10, 10));
            session.save(new Candidate(0, "cand2", 20, 20));
            session.save(new Candidate(0, "cand3", 30, 30));


            List<Candidate> list = session.createQuery("from Candidate", Candidate.class)
                    .getResultList();

            list = session.createQuery("from Candidate s where s.id = 1", Candidate.class)
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