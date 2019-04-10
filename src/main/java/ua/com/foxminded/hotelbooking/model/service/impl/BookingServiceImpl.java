package ua.com.foxminded.hotelbooking.model.service.impl;

import org.springframework.stereotype.Service;
import ua.com.foxminded.hotelbooking.model.entity.Booking;
import ua.com.foxminded.hotelbooking.model.entity.User;
import ua.com.foxminded.hotelbooking.model.repository.BookingRepository;
import ua.com.foxminded.hotelbooking.model.service.BookingService;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> getByUser(User user) {
        return bookingRepository.findAllByUser(user);
    }

    @Override
    public Booking create(Booking booking) {
        if (booking.getId() == 0) {
            return bookingRepository.saveAndFlush(booking);
        }
        return booking;
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public boolean update(Booking object) {
        throw new UnsupportedOperationException("Update booking is not supported yet.");
    }

    @Override
    public Optional<Booking> getById(long id) {
        throw new UnsupportedOperationException("Get booking by id is not supported yet.");
    }

    @Override
    public boolean delete(long id) {
        throw new UnsupportedOperationException("Delete booking is not supported yet.");
    }

    @Override
    public boolean isExisting(long id) {
        throw new UnsupportedOperationException("Is existing by id for booking is not supported yet.");
    }
}
