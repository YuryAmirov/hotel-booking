package ua.com.foxminded.hotelbooking.model.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.com.foxminded.hotelbooking.model.entity.Room;
import ua.com.foxminded.hotelbooking.model.entity.core.RoomCategory;
import ua.com.foxminded.hotelbooking.model.service.BookingService;
import ua.com.foxminded.hotelbooking.model.service.RoomService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(RoomController.class)
@AutoConfigureMockMvc
public class RoomControllerTest {
    private static final String ROOMS_ATTRIBUTE_NAME = "rooms";

    private static final String CATEGORY_ATTRIBUTE_NAME = "category";

    private static final String ROOM_CATEGORIES_ATTRIBUTE_NAME = "categories";

    @Autowired
    private MockMvc server;

    @MockBean
    private RoomService roomService;

    @MockBean
    private BookingService bookingService;

    private Room room;

    @Before
    public void SetUp() {
        room = new Room();
        room.setNumber(1);
        room.setCategory(RoomCategory.SINGLE);
        room.setPricePerNight(100);
    }

    @Test
    public void invokeGetAllPage() throws Exception{
        when(roomService.getAll()).thenReturn(Collections.singletonList(room));

        server.perform(get("/rooms/all"))
                .andExpect(status().isOk())
                .andExpect(model().attribute(ROOMS_ATTRIBUTE_NAME, Collections.singletonList(room)))
                .andExpect(view().name(containsString("rooms/all")));
    }

    @Test
    public void invokeGetByCategory() throws Exception{
        when(roomService.getByCategory(RoomCategory.SINGLE)).thenReturn(Collections.singletonList(room));

        server.perform(get("/rooms/category")
                        .param("category", "SINGLE"))
                .andExpect(status().isOk())
                .andExpect(model().attribute(ROOMS_ATTRIBUTE_NAME, Collections.singletonList(room)))
                .andExpect(model().attribute(ROOM_CATEGORIES_ATTRIBUTE_NAME, new ArrayList<>(EnumSet.allOf(RoomCategory.class))))
                .andExpect(model().attribute(CATEGORY_ATTRIBUTE_NAME, RoomCategory.SINGLE))
                .andExpect(view().name(containsString("rooms/category")));
    }
}
