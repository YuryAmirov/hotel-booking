package ua.com.foxminede.hotelbooking.model.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ua.com.foxminede.hotelbooking.model.entity.Room;
import ua.com.foxminede.hotelbooking.model.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@Log4j2
public class RoomController {

    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ModelAndView getAll(ModelAndView modelAndView) {
        List<Room> rooms = roomService.getAll();
        modelAndView.addObject("rooms", rooms);
        return modelAndView;
    }
}
