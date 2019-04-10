package ua.com.foxminded.hotelbooking.model.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.foxminded.hotelbooking.model.entity.Booking;
import ua.com.foxminded.hotelbooking.model.entity.User;
import ua.com.foxminded.hotelbooking.model.repository.BookingRepository;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceImplTest {

    @Mock
    private BookingRepository repository;

    @Spy
    @InjectMocks
    BookingServiceImpl bookingService;

    private Booking booking;

    private User user;

    @Before
    public void setUp() {
        user = new User();
        booking = new Booking();
    }

    @Test
    public void whenGetByUserInvoked() {
        bookingService.getByUser(user);

        verify(repository).findAllByUser(user);
    }

    @Test
    public void whenCreateInvoked() {
        bookingService.create(booking);

        verify(repository).saveAndFlush(booking);
    }

    @Test
    public void whenGetAllInvoked() {
        bookingService.getAll();

        verify(repository).findAll();
    }
}
