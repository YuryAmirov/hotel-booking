package ua.com.foxminede.hotelbooking.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminede.hotelbooking.model.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
