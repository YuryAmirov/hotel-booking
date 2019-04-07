package ua.com.foxminded.hotelbooking.model.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ua.com.foxminded.hotelbooking.model.entity.User;
import ua.com.foxminded.hotelbooking.model.service.UserService;

import javax.persistence.EntityExistsException;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final String USER_ATTRIBUTE_NAME = "User";

    private static final String ERROR_MESSAGES_ATTRIBUTE_NAME = "errorMessage";

    private static final String SUCCESS_MESSAGES_ATTRIBUTE_NAME = "successMessage";

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public ModelAndView getRegistrationPage(ModelAndView modelAndView) {
        modelAndView.addObject(USER_ATTRIBUTE_NAME, new User());
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView doRegistration(ModelAndView modelAndView,
                                       @ModelAttribute(USER_ATTRIBUTE_NAME) User user) {
        try {
            userService.create(user);
            modelAndView.addObject(SUCCESS_MESSAGES_ATTRIBUTE_NAME, "New user registered successfully!");
        } catch (EntityExistsException e) {
            System.out.println(e.getMessage());
            modelAndView.addObject(ERROR_MESSAGES_ATTRIBUTE_NAME, "Registration failed!");
        }

        return modelAndView;
    }
}
