package ua.com.foxminede.hotelbooking.model.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.com.foxminede.hotelbooking.model.entity.Booking;
import ua.com.foxminede.hotelbooking.model.repository.BookingRepository;
import ua.com.foxminede.hotelbooking.model.service.BookingService;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking create(Booking object) {
        return null;
    }

    @Override
    public boolean update(Booking object) {
        return false;
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> getById(long id) {
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
}
