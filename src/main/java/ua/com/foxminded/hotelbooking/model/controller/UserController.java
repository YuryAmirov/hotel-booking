package ua.com.foxminded.hotelbooking.model.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/registration")
    public ModelAndView getRegistrationPage(ModelAndView modelAndView) {
        return modelAndView;
    }
}
