package ua.com.foxminded.hotelbooking.model.service;

import ua.com.foxminded.hotelbooking.model.entity.Room;
import ua.com.foxminded.hotelbooking.model.entity.core.RoomCategory;

import java.util.List;

public interface RoomService extends GenericService<Room> {

    List<Room> getByCategory(RoomCategory category);
}
