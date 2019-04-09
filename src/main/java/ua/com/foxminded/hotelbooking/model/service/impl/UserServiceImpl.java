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
    public boolean update(User object) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public Optional<User> getById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean isExisting(long id) {
        return false;
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean isExisting(String email) {
        return userRepository.existsByEmail(email);
    }
}
