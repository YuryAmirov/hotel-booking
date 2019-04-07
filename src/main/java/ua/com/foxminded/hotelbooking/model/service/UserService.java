package ua.com.foxminded.hotelbooking.model.service;

import ua.com.foxminded.hotelbooking.model.entity.User;

public interface UserService extends GenericService<User> {

    boolean isExisting(String email);
}
