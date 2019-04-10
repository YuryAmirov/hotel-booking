package ua.com.foxminded.hotelbooking.model.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.foxminded.hotelbooking.model.entity.core.RoomCategory;
import ua.com.foxminded.hotelbooking.model.repository.RoomRepository;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @Spy
    @InjectMocks
    private RoomServiceImpl roomService;

    @Test
    public void whenGetAllInvoked() {
        roomService.getAll();

        verify(roomRepository).findAll();
    }

    @Test
    public void whenGetByIdInvoked() {
        roomService.getById(1);

        verify(roomRepository).findById(1l);
    }

    @Test
    public void whenGetByCategoryInvoked() {
        roomService.getByCategory(RoomCategory.SINGLE);

        verify(roomRepository).findByCategory(RoomCategory.SINGLE);
    }
}
