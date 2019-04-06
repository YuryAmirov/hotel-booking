package ua.com.foxminded.hotelbooking.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.hotelbooking.model.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
