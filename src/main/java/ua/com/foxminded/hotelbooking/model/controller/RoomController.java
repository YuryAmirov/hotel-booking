package ua.com.foxminded.hotelbooking.model.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ua.com.foxminded.hotelbooking.model.entity.Booking;
import ua.com.foxminded.hotelbooking.model.entity.Period;
import ua.com.foxminded.hotelbooking.model.entity.Room;
import ua.com.foxminded.hotelbooking.model.entity.core.RoomCategory;
import ua.com.foxminded.hotelbooking.model.service.BookingService;
import ua.com.foxminded.hotelbooking.model.service.RoomService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private static final String ROOMS_ATTRIBUTE_NAME = "rooms";

    private static final String START_DATE_ATTRIBUTE_NAME = "startDate";

    private static final String END_DATE_ATTRIBUTE_NAME = "endDate";

    private static final String CATEGORY_ATTRIBUTE_NAME = "category";

    private static final String ROOM_CATEGORIES_ATTRIBUTE_NAME = "categories";

    private static final String CATEGORY_FROM_LAST_FILTER_ATTRIBUTE_NAME = "categoryFromLastFilter";

    private RoomService roomService;

    private BookingService bookingService;

    public RoomController(RoomService roomService, BookingService bookingService) {
        this.roomService = roomService;
        this.bookingService = bookingService;
    }

    @GetMapping("/all")
    public ModelAndView getAll(ModelAndView modelAndView) {
        List<Room> rooms = roomService.getAll();
        modelAndView.addObject(ROOMS_ATTRIBUTE_NAME, rooms);
        return modelAndView;
    }

    @GetMapping("/available")
    public ModelAndView getAvailable(ModelAndView modelAndView, String startDate, String endDate) {
        List<Room> rooms = roomService.getAll();
        List<Booking> bookings = bookingService.getAll();

        filterByPeriod(bookings, startDate, endDate);
        filterByAvailability(rooms, bookings);

        modelAndView.addObject(ROOMS_ATTRIBUTE_NAME, rooms);
        modelAndView.addObject(START_DATE_ATTRIBUTE_NAME, startDate);
        modelAndView.addObject(END_DATE_ATTRIBUTE_NAME, endDate);

        return modelAndView;
    }

    @GetMapping("/category")
    public ModelAndView getByCategory(ModelAndView modelAndView, RoomCategory category) {
        List<Room> rooms = roomService.getAll();

        List<RoomCategory> categories = new ArrayList<>(EnumSet.allOf(RoomCategory.class));
        filterByCategory(rooms, category);

        modelAndView.addObject(ROOM_CATEGORIES_ATTRIBUTE_NAME, categories);
        modelAndView.addObject(CATEGORY_FROM_LAST_FILTER_ATTRIBUTE_NAME, category);
        modelAndView.addObject(ROOMS_ATTRIBUTE_NAME, rooms);
        modelAndView.addObject(CATEGORY_ATTRIBUTE_NAME, category);

        return modelAndView;
    }

    private void filterByCategory(List<Room> rooms, RoomCategory category) {
        if(category != null) {
            rooms.removeIf(room -> !room.getCategory().equals(category));
        }
    }

    private void filterByPeriod(List<Booking> bookings, String startDateString, String endDateString) {
        if (areDatesPresent(startDateString, endDateString)) {
            Timestamp startDate = getTimestampFromString(startDateString);
            Timestamp endDate = getTimestampFromString(endDateString);
            Period period = new Period(startDate, endDate);

            bookings.removeIf(booking -> !isOverlapping(booking.getPeriod(), period));
        }
    }

    private boolean areDatesPresent(String startDateString, String endDateString) {
        return startDateString != null && !startDateString.equals("") && endDateString != null && !endDateString.equals("");
    }

    private boolean isOverlapping(Period bookingPeriod, Period searchPeriod) {
        return isInPeriod(bookingPeriod.getStartDate(), searchPeriod) || isInPeriod(bookingPeriod.getEndDate(), searchPeriod);
    }

    private boolean isInPeriod(Timestamp date, Period searchPeriod) {
        return date.after(searchPeriod.getStartDate()) && date.before(searchPeriod.getEndDate());
    }

    private void filterByAvailability(List<Room> rooms, List<Booking> bookings) {
        List<Room> bookedRooms = bookings.stream()
                .map(Booking::getRoom).collect(Collectors.toList());
        rooms.removeAll(bookedRooms);
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
}
