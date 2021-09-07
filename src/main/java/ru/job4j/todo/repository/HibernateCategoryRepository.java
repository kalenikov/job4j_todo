package ru.job4j.todo.repository;

import ru.job4j.todo.model.Category;

import java.util.List;

import static ru.job4j.todo.HibernateUtil.doInTransactionWithReturn;

public class HibernateCategoryRepository implements CategoryRepository {

    private static final class SingletonHolder {
        private static final HibernateCategoryRepository INSTANCE = new HibernateCategoryRepository();
    }

    public static HibernateCategoryRepository getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public List<Category> getAll() {
        return doInTransactionWithReturn(session ->
                session.createQuery("from Category order by name", Category.class)
                        .getResultList());
    }

    @Override
    public Category get(int id) {
        return doInTransactionWithReturn(session -> session.get(Category.class, id));
    }
}
