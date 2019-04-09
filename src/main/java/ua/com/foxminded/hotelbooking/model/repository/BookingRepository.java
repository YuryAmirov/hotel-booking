package ua.com.foxminded.hotelbooking.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.hotelbooking.model.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
