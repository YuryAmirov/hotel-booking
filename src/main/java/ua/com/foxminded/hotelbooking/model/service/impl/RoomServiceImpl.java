package ua.com.foxminded.hotelbooking.model.service.impl;

import org.springframework.stereotype.Service;
import ua.com.foxminded.hotelbooking.model.entity.Room;
import ua.com.foxminded.hotelbooking.model.entity.core.RoomCategory;
import ua.com.foxminded.hotelbooking.model.repository.RoomRepository;
import ua.com.foxminded.hotelbooking.model.service.RoomService;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> getById(long id) {
        return roomRepository.findById(id);
    }

    @Override
    public List<Room> getByCategory(RoomCategory category) {
        return roomRepository.findByCategory(category);
    }

    @Override
    public boolean delete(long id) {
        throw new UnsupportedOperationException("Delete room is not supported yet.");
    }

    @Override
    public Room create(Room object) {
        throw new UnsupportedOperationException("Create room is not supported yet.");
    }

    @Override
    public boolean update(Room object) {
        throw new UnsupportedOperationException("Update room is not supported yet.");
    }

    @Override
    public boolean isExisting(long id) {
        throw new UnsupportedOperationException("Is room existing is not supported yet.");
    }
}
