package ru.job4j.todo.repository;

import ru.job4j.todo.model.User;

import java.util.Optional;

import static ru.job4j.todo.HibernateUtil.doInTransaction;
import static ru.job4j.todo.HibernateUtil.doInTransactionWithReturn;

public class HibernateUserRepository implements UserRepository {

    private static final class SingletonHolder {
        private static final HibernateUserRepository INSTANCE = new HibernateUserRepository();
    }

    public static HibernateUserRepository getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public User save(User user) {
        doInTransaction(session -> session.saveOrUpdate(user));
        return user;
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return doInTransactionWithReturn(
                session -> session
                        .createQuery("from User where email=:email", User.class)
                        .setParameter("email", email)
                        .uniqueResultOptional());
    }
}
