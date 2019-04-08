package ua.com.foxminded.hotelbooking.model.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ua.com.foxminded.hotelbooking.model.entity.Room;
import ua.com.foxminded.hotelbooking.model.service.RoomService;

import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private static final String CHOOSE_ROOM_ATTRIBUTE_NAME = "chooseRoom";

    private static final String ENTER_DATES_ATTRIBUTE_NAME = "enterDates";

    private static final String START_DATE_ATTRIBUTE_NAME = "startDate";

    private static final String END_DATE_ATTRIBUTE_NAME = "endDate";

    private static final String ERROR_MESSAGES_ATTRIBUTE_NAME = "errorMessage";

    private static final String SUCCESS_MESSAGES_ATTRIBUTE_NAME = "successMessage";

    private static final String ROOM_ATTRIBUTE_NAME = "room";

    private static final String USER_EMAIL_ATTRIBUTE_NAME = "userEmail";

    private static final String USER_PASSWORD_ATTRIBUTE_NAME = "userPassword";

    private static final String PRICE_ATTRIBUTE_NAME = "price";

    private RoomController roomController;

    private RoomService roomService;

    public BookingController(RoomController roomController, RoomService roomService) {
        this.roomController = roomController;
        this.roomService = roomService;
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
    public ModelAndView getCreateBookingPage(ModelAndView modelAndView,
                                             String startDate,
                                             String endDate,
                                             Long roomId) {
        Optional<Room> room = roomService.getById(roomId);
        if(!room.isPresent()) {
            return modelAndView.addObject(ERROR_MESSAGES_ATTRIBUTE_NAME, "The room doesn't exist!");
        }

        int price = calculatePrice(room.get(), startDate, endDate);

        modelAndView.addObject(ROOM_ATTRIBUTE_NAME, room.get());
        modelAndView.addObject(START_DATE_ATTRIBUTE_NAME, startDate);
        modelAndView.addObject(END_DATE_ATTRIBUTE_NAME, endDate);
        modelAndView.addObject(PRICE_ATTRIBUTE_NAME, price);
        modelAndView.addObject(USER_EMAIL_ATTRIBUTE_NAME, new StringBuilder());
        modelAndView.addObject(USER_PASSWORD_ATTRIBUTE_NAME, new StringBuilder());

        return modelAndView;
    }

    private int calculatePrice(Room room, String startDate, String endDate) {
        return 0;
    }

    private boolean areDatesPresent(String startDateString, String endDateString) {
        return startDateString != null && !startDateString.equals("") && endDateString != null && !endDateString.equals("");
    }
}
