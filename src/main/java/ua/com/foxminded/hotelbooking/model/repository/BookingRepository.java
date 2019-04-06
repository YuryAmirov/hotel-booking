package ua.com.foxminded.hotelbooking.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.hotelbooking.model.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
