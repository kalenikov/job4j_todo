package ru.job4j.todo.repository;

import org.hibernate.Session;
import ru.job4j.todo.HibernateUtil;
import ru.job4j.todo.model.Item;

import java.sql.SQLException;
import java.util.List;

public class HibernateItemRepository implements ItemRepository {

    private static final class SingletonHolder {
        private static final HibernateItemRepository INSTANCE = new HibernateItemRepository();
    }

    public static HibernateItemRepository getInstance() {
        return SingletonHolder.INSTANCE;
    }


    @Override
    public Item save(Item item) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public List<Item> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Item> result = session.createQuery("from Item order by id", Item.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Item get(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Item item = session.find(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return item;
    }
}
