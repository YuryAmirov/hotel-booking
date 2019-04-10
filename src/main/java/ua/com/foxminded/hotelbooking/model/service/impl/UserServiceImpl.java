package ua.com.foxminded.hotelbooking.model.service.impl;

import org.springframework.stereotype.Service;
import ua.com.foxminded.hotelbooking.model.entity.User;
import ua.com.foxminded.hotelbooking.model.repository.UserRepository;
import ua.com.foxminded.hotelbooking.model.service.UserService;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        if (isExisting(user.getEmail())) {
            throw new EntityExistsException(String.format("User with email: '%s' already exists.", user.getEmail()));
        }

        return userRepository.saveAndFlush(user);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean isExisting(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean update(User object) {
        throw new UnsupportedOperationException("Update user is not supported yet.");
    }

    @Override
    public List<User> getAll() {
        throw new UnsupportedOperationException("Get all users is not supported yet.");
    }

    @Override
    public Optional<User> getById(long id) {
        throw new UnsupportedOperationException("Get by id for user is not supported yet.");
    }

    @Override
    public boolean delete(long id) {
        throw new UnsupportedOperationException("Delete user is not supported yet.");
    }

    @Override
    public boolean isExisting(long id) {
        throw new UnsupportedOperationException("Is user existing is not supported yet.");
    }
}
