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
import ua.com.foxminded.hotelbooking.model.entity.Booking;
import ua.com.foxminded.hotelbooking.model.entity.Period;
import ua.com.foxminded.hotelbooking.model.entity.Room;
import ua.com.foxminded.hotelbooking.model.entity.User;
import ua.com.foxminded.hotelbooking.model.entity.core.RoomCategory;
import ua.com.foxminded.hotelbooking.model.service.BookingService;
import ua.com.foxminded.hotelbooking.model.service.RoomService;
import ua.com.foxminded.hotelbooking.model.service.UserService;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BookingController.class)
@AutoConfigureMockMvc
public class BookingControllerTest {

    private static final String BOOKINGS_ATTRIBUTE_NAME = "bookings";

    private static final String START_DATE = "2019-04-03 00:00:00.0";

    private static final String END_DATE = "2019-04-04 00:00:00.0";

    private static final String START_DATE_ATTRIBUTE_NAME = "startDate";

    private static final String END_DATE_ATTRIBUTE_NAME = "endDate";

    private static final String ROOM_ATTRIBUTE_NAME = "room";

    private static final String SUCCESS_MESSAGE_ATTRIBUTE_NAME = "successMessage";

    private static final String NO_ROOM_ATTRIBUTE_NAME = "noRoomMessage";

    @Autowired
    private MockMvc server;

    @MockBean
    private RoomService roomService;

    @MockBean
    private UserService userService;

    @MockBean
    private BookingService bookingService;

    @MockBean
    private RoomController roomController;

    private Booking booking;

    private Room room;

    private User user;

    private Period period;

    @Before
    public void setUp() {
        room = new Room();
        room.setNumber(1);
        room.setCategory(RoomCategory.SINGLE);
        room.setPricePerNight(10);
        user = new User();
        user.setFirstName("first name");
        user.setLastName("last name");
        user.setPassword("password");
        user.setEmail("user@email.com");
        period = new Period(Timestamp.valueOf(START_DATE), Timestamp.valueOf(END_DATE));
        booking = new Booking();
        booking.setUser(user);
        booking.setRoom(room);
        booking.setPeriod(period);
    }

    @Test
    public void invokeGetAllBookings() throws Exception {
        when(bookingService.getAll()).thenReturn(Collections.singletonList(booking));

        server.perform(get("/bookings/all"))
                .andExpect(status().isOk())
                .andExpect(model().attribute(BOOKINGS_ATTRIBUTE_NAME, Collections.singletonList(booking)))
                .andExpect(view().name(containsString("bookings/all")));
    }

    @Test
    public void invokeGetCreateBookingPage() throws Exception {
        when(roomService.getById(1)).thenReturn(Optional.of(room));

        server.perform(get("/bookings/create")
                        .param("startDate", "2019-04-03")
                        .param("endDate", "2019-04-04")
                        .param("roomId", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("user", new User()))
                .andExpect(model().attribute(ROOM_ATTRIBUTE_NAME, room))
                .andExpect(model().attribute(START_DATE_ATTRIBUTE_NAME, "2019-04-03"))
                .andExpect(model().attribute(END_DATE_ATTRIBUTE_NAME, "2019-04-04"))
                .andExpect(view().name(containsString("bookings/create")));
    }

    @Test
    public void doPostCreateBooking() throws Exception {
        when(roomService.getById(1)).thenReturn(Optional.of(room));
        when(userService.getByEmail("user@email.com")).thenReturn(Optional.of(user));

        server.perform(post("/bookings/create")
                .param("startDate", "2019-04-03")
                .param("endDate", "2019-04-04")
                .param("roomId", "1")
                .flashAttr("user", user))
                .andExpect(status().isOk())
                .andExpect(model().attribute(ROOM_ATTRIBUTE_NAME, room))
                .andExpect(model().attribute(START_DATE_ATTRIBUTE_NAME, "2019-04-03"))
                .andExpect(model().attribute(END_DATE_ATTRIBUTE_NAME, "2019-04-04"))
                .andExpect(model().attribute(SUCCESS_MESSAGE_ATTRIBUTE_NAME, "The room was booked successfully!"))
                .andExpect(view().name(containsString("bookings/create")));
    }

    @Test
    public void failDoPostCreateBookingWhenWrongRoomId() throws Exception {
        when(roomService.getById(1)).thenReturn(Optional.empty());
        when(userService.getByEmail("user@email.com")).thenReturn(Optional.of(user));

        server.perform(post("/bookings/create")
                .param("startDate", "2019-04-03")
                .param("endDate", "2019-04-04")
                .param("roomId", "1")
                .flashAttr("user", user))
                .andExpect(status().isOk())
                .andExpect(model().attribute(NO_ROOM_ATTRIBUTE_NAME, "The room doesn't exist!"))
                .andExpect(view().name(containsString("bookings/create")));
    }
}
