package ua.com.foxminede.hotelbooking.model.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.com.foxminede.hotelbooking.model.entity.Room;
import ua.com.foxminede.hotelbooking.model.repository.RoomRepository;
import ua.com.foxminede.hotelbooking.model.service.RoomService;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room create(Room object) {
        return null;
    }

    @Override
    public boolean update(Room object) {
        return false;
    }

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> getById(long id) {
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
