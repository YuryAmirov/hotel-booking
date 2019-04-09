package ua.com.foxminded.hotelbooking.model.service;

import ua.com.foxminded.hotelbooking.model.entity.User;

import java.util.Optional;

public interface UserService extends GenericService<User> {

    boolean isExisting(String email);

    Optional<User> getByEmail(String email);
}
