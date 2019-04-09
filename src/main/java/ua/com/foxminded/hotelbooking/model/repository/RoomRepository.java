package ua.com.foxminded.hotelbooking.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.hotelbooking.model.entity.Room;
import ua.com.foxminded.hotelbooking.model.entity.core.RoomCategory;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByCategory(RoomCategory category);
}
