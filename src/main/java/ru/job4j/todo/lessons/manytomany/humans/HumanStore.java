package ru.job4j.todo.lessons.manytomany.humans;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HumanStore implements AutoCloseable {
    private SessionFactory sf;

    private HumanStore() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sf = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
    }

    private static final class Lazy {
        private static final HumanStore INSTANCE = new HumanStore();
    }

    public static HumanStore instanceOf() {
        return Lazy.INSTANCE;
    }

    public void addNewHuman(Human human, String[] ids) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();

            for (String id : ids) {
                City city = session.find(City.class, Integer.parseInt(id));
                human.addCity(city);
            }
            session.save(human);

            session.getTransaction().commit();
        } catch (Exception e) {
            sf.getCurrentSession().getTransaction().rollback();
        }
    }

    public List<City> allCities() {
        List<City> rsl = new ArrayList<>();
        try (Session session = sf.openSession()) {
            session.beginTransaction();

            rsl = session.createQuery("select c from City c", City.class).list();

            session.getTransaction().commit();
        } catch (Exception e) {
            sf.getCurrentSession().getTransaction().rollback();
        }
        return rsl;
    }

    @Override
    public void close() throws Exception {
        sf.close();
    }

    public static void main(String[] args) {
        System.out.println(HumanStore.instanceOf().allCities());;
    }
}