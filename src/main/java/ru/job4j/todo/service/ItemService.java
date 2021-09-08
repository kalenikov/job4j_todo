package ru.job4j.todo.service;

import ru.job4j.todo.model.Item;
import ru.job4j.todo.repository.HibernateCategoryRepository;
import ru.job4j.todo.repository.HibernateItemRepository;
import ru.job4j.todo.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

public class ItemService {
    private static final class SingletonHolder {
        private static final ItemService INSTANCE = new ItemService();
    }

    public static ItemService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public List<Item> getAll() {
        return HibernateItemRepository.getInstance().getAll();
    }

    public Item save(Item dto) {
        //attach all categories to current session
        dto.getCategories().forEach(category -> HibernateCategoryRepository.getInstance().get(category.getId()));
        ItemRepository repo = HibernateItemRepository.getInstance();
        if (dto.getId() != 0) {
            Optional<Item> stored = Optional.ofNullable(repo.get(dto.getId()));
            if (stored.isPresent()) {
                dto.setCreated(stored.get().getCreated());
                if (dto.getDescription() == null) {
                    dto.setDescription(stored.get().getDescription());
                }
            }
        }
        return repo.save(dto);
    }
}
