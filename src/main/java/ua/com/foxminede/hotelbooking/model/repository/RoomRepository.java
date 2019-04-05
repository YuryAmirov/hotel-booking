package ua.com.foxminede.hotelbooking.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminede.hotelbooking.model.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
