package ua.com.foxminded.hotelbooking.model.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<E> {

    E create(E object);

    boolean update(E object);

    List<E> getAll();

    Optional<E> getById(long id);

    boolean delete(long id);

    boolean isExisting(long id);
}
