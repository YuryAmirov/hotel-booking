package ua.com.foxminded.hotelbooking.model.service;

import ua.com.foxminded.hotelbooking.model.entity.Booking;
import ua.com.foxminded.hotelbooking.model.entity.User;

import java.util.List;

public interface BookingService extends GenericService<Booking> {

    List<Booking> getByUser(User user);
}
