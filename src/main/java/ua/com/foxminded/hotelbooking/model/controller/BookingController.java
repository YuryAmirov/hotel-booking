package ua.com.foxminded.hotelbooking.model.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private static final String CHOOSE_ROOM_ATTRIBUTE_NAME = "chooseRoom";

    private static final String ENTER_DATES_ATTRIBUTE_NAME = "enterDates";

    private RoomController roomController;

    public BookingController(RoomController roomController) {
        this.roomController = roomController;
    }

    @GetMapping("/choose-room")
    public ModelAndView getCreateBookingPage(ModelAndView modelAndView, String startDate, String endDate) {
        modelAndView = roomController.getAvailable(modelAndView, startDate, endDate);
        modelAndView.setViewName("rooms/available");
        if(areDatesPresent(startDate, endDate)) {
            modelAndView.addObject(CHOOSE_ROOM_ATTRIBUTE_NAME, true);
        } else {
            modelAndView.addObject(ENTER_DATES_ATTRIBUTE_NAME, true);
        }
        return modelAndView;
    }

    private boolean areDatesPresent(String startDateString, String endDateString) {
        return startDateString != null && !startDateString.equals("") && endDateString != null && !endDateString.equals("");
    }
}
