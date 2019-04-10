package ua.com.foxminded.hotelbooking.model.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ua.com.foxminded.hotelbooking.model.entity.AdditionalOption;
import ua.com.foxminded.hotelbooking.model.entity.Booking;
import ua.com.foxminded.hotelbooking.model.entity.Period;
import ua.com.foxminded.hotelbooking.model.entity.Room;
import ua.com.foxminded.hotelbooking.model.entity.User;
import ua.com.foxminded.hotelbooking.model.service.BookingService;
import ua.com.foxminded.hotelbooking.model.service.RoomService;
import ua.com.foxminded.hotelbooking.model.service.UserService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private static final String CHOOSE_ROOM_ATTRIBUTE_NAME = "chooseRoom";

    private static final String ENTER_DATES_ATTRIBUTE_NAME = "enterDates";

    private static final String START_DATE_ATTRIBUTE_NAME = "startDate";

    private static final String END_DATE_ATTRIBUTE_NAME = "endDate";

    private static final String NO_ROOM_ATTRIBUTE_NAME = "noRoomMessage";

    private static final String SUCCESS_MESSAGE_ATTRIBUTE_NAME = "successMessage";

    private static final String ROOM_ATTRIBUTE_NAME = "room";

    private static final String PRICE_ATTRIBUTE_NAME = "price";

    private static final String ERROR_MESSAGE_ATTRIBUTE_NAME = "errorMessage";

    private static final String BOOKINGS_ATTRIBUTE_NAME = "bookings";

    private static final String EMAIL_FROM_LAST_ENTER_ATTRIBUTE_NAME = "emailFromLastEnter";

    private static final String USER_EMAIL_ATTRIBUTE_NAME = "userEmail";

    private static final String ENTER_EMAIL_ATTRIBUTE_NAME = "enterEmail";

    private RoomController roomController;

    private RoomService roomService;

    private UserService userService;

    private BookingService bookingService;

    public BookingController(RoomController roomController,
                             RoomService roomService,
                             UserService userService,
                             BookingService bookingService) {
        this.roomController = roomController;
        this.roomService = roomService;
        this.userService = userService;
        this.bookingService = bookingService;
    }

    @GetMapping("/all")
    public ModelAndView getAllBookings() {
        ModelAndView modelAndView = new ModelAndView();
        List<Booking> bookings = bookingService.getAll();
        modelAndView.addObject(BOOKINGS_ATTRIBUTE_NAME, bookings);
        return modelAndView;
    }

    @GetMapping("/by-user")
    public ModelAndView getByUser(ModelAndView modelAndView, String userEmail) {
        if(userEmail == null) {
            return modelAndView.addObject(ENTER_EMAIL_ATTRIBUTE_NAME, "Enter user email first!");
        }

        Optional<User> user = userService.getByEmail(userEmail);

        if(!user.isPresent()) {
            return modelAndView.addObject(ERROR_MESSAGE_ATTRIBUTE_NAME, "Incorrect email!");
        }

        List<Booking> bookings = bookingService.getByUser(user.get());

        modelAndView.addObject(EMAIL_FROM_LAST_ENTER_ATTRIBUTE_NAME, userEmail);
        modelAndView.addObject(USER_EMAIL_ATTRIBUTE_NAME, userEmail);
        modelAndView.addObject(BOOKINGS_ATTRIBUTE_NAME, bookings);

        return modelAndView;
    }

    @GetMapping("/choose-room")
    public ModelAndView getChooseRoomPage(ModelAndView modelAndView, String startDate, String endDate) {
        modelAndView = roomController.getAvailable(modelAndView, startDate, endDate);
        modelAndView.setViewName("rooms/available");
        if(areDatesPresent(startDate, endDate)) {
            modelAndView.addObject(CHOOSE_ROOM_ATTRIBUTE_NAME, true);
            modelAndView.addObject(START_DATE_ATTRIBUTE_NAME, startDate);
            modelAndView.addObject(END_DATE_ATTRIBUTE_NAME, endDate);
        } else {
            modelAndView.addObject(ENTER_DATES_ATTRIBUTE_NAME, true);
        }
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView getCreateBookingPage(String startDate,
                                             String endDate,
                                             Long roomId) {

        ModelAndView modelAndView = getPopulatedModelAndView(startDate, endDate, roomId);
        modelAndView.addObject("user", new User());

        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView doCreateBooking(String startDate,
                                        String endDate,
                                        Long roomId,
                                        @ModelAttribute("user") User user) {
        ModelAndView modelAndView = getPopulatedModelAndView(startDate, endDate, roomId);
        Optional<Room> room = roomService.getById(roomId);
        if(!room.isPresent()) {
            return modelAndView;
        }

        Optional<User> userActual = userService.getByEmail(user.getEmail());

        if(!userActual.isPresent() || !userActual.get().getPassword().equals(user.getPassword())) {
            return modelAndView.addObject(ERROR_MESSAGE_ATTRIBUTE_NAME, "Incorrect email or password!");
        }

        Period period = generatePeriod(startDate, endDate);
        Booking booking = new Booking();
        booking.setRoom(room.get());
        booking.setUser(userActual.get());
        booking.setPeriod(period);

        bookingService.create(booking);

        return modelAndView.addObject(SUCCESS_MESSAGE_ATTRIBUTE_NAME, "The room was booked successfully!");
    }

    private Period generatePeriod(String startDateString, String endDateString) {
            Timestamp startDate = getTimestampFromString(startDateString);
            Timestamp endDate = getTimestampFromString(endDateString);

            return new Period(startDate, endDate);
    }

    private Timestamp getTimestampFromString(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp timestampDate;
        try {
            timestampDate = new Timestamp(dateFormat.parse(date).getTime());
        } catch (ParseException e) {
            System.out.println(String.format("Cast String to Timestamp failed! '%s", e.getMessage()));
            return null;
        }
        return timestampDate;
    }

    private ModelAndView getPopulatedModelAndView(String startDate, String endDate, Long roomId) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Room> room = roomService.getById(roomId);
        if(!room.isPresent()) {
            return modelAndView.addObject(NO_ROOM_ATTRIBUTE_NAME, "The room doesn't exist!");
        }

        int price = calculatePrice(room.get(), startDate, endDate);

        modelAndView.addObject(ROOM_ATTRIBUTE_NAME, room.get());
        modelAndView.addObject(START_DATE_ATTRIBUTE_NAME, startDate);
        modelAndView.addObject(END_DATE_ATTRIBUTE_NAME, endDate);
        modelAndView.addObject(PRICE_ATTRIBUTE_NAME, price);

        return modelAndView;
    }

    private int calculatePrice(Room room, String startDate, String endDate) {
        int numberOfDays = calculateNumberOfDays(startDate, endDate);
        int price = room.getPricePerNight();
        for(AdditionalOption option : room.getAdditionalOptions()) {
            price += option.getPricePerNight();
        }
        return price * numberOfDays;
    }

    private boolean areDatesPresent(String startDateString, String endDateString) {
        return startDateString != null && !startDateString.equals("") && endDateString != null && !endDateString.equals("");
    }

    private int calculateNumberOfDays(String startDateString, String endDateString) {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalDate endDate = LocalDate.parse(endDateString);

        return startDate.until(endDate).getDays();
    }
}
