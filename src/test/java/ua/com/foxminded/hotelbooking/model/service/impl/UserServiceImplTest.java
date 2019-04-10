package ua.com.foxminded.hotelbooking.model.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.foxminded.hotelbooking.model.entity.User;
import ua.com.foxminded.hotelbooking.model.repository.UserRepository;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UserRepository repository;

    @Spy
    @InjectMocks
    UserServiceImpl userService;

    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void whenCreateInvoked() {
        userService.create(user);

        verify(repository).saveAndFlush(user);
    }

    @Test
    public void whenGetByEmailInvoked() {
        userService.getByEmail("user@mail.com");

        verify(repository).findByEmail("user@mail.com");
    }

    @Test
    public void whenIsExistingByEmailInvoked() {
        userService.isExisting("user@mail.com");

        verify(repository).existsByEmail("user@mail.com");
    }
}
