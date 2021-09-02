package ru.job4j.todo.repository;

import ru.job4j.todo.model.Item;

import java.util.List;

import static ru.job4j.todo.HibernateUtil.doInTransaction;
import static ru.job4j.todo.HibernateUtil.doInTransactionWithReturn;

public class HibernateItemRepository implements ItemRepository {

    private static final class SingletonHolder {
        private static final HibernateItemRepository INSTANCE = new HibernateItemRepository();
    }

    public static HibernateItemRepository getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public Item save(Item item) {
        doInTransaction(session -> session.saveOrUpdate(item));
        return item;
    }

    @Override
    public List<Item> getAll() {
        return doInTransactionWithReturn(session -> session.createQuery("from Item order by id", Item.class).getResultList());
    }

    @Override
    public Item get(Integer id) {
        return doInTransactionWithReturn(session -> session.find(Item.class, id));
    }
}
