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
import ua.com.foxminded.hotelbooking.model.entity.User;
import ua.com.foxminded.hotelbooking.model.service.UserService;

import javax.persistence.EntityExistsException;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    private static final String USER_ATTRIBUTE_NAME = "User";

    private static final String ERROR_MESSAGES_ATTRIBUTE_NAME = "errorMessage";

    private static final String SUCCESS_MESSAGES_ATTRIBUTE_NAME = "successMessage";

    @Autowired
    private MockMvc server;

    @MockBean
    private UserService userService;

    private User user;

    @Before
    public void setUp() {
        user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("user@mail.com");
        user.setPassword("AAaa!");
    }

    @Test
    public void invokeRegistrationPage() throws Exception{
        server.perform(get("/users/registration"))
                .andExpect(status().isOk())
                .andExpect(model().attribute(USER_ATTRIBUTE_NAME, new User()))
                .andExpect(view().name(containsString("users/registration")));
    }

    @Test
    public void  successWhenDoRegistration() throws Exception {
        server.perform(post("/users/registration")
                        .flashAttr(USER_ATTRIBUTE_NAME, user))
                .andExpect(status().isOk())
                .andExpect(model().attribute(SUCCESS_MESSAGES_ATTRIBUTE_NAME, "New user registered successfully!"))
                .andExpect(view().name(containsString("users/registration")));

    }

    @Test
    public void  failWhenDoRegistrationWithExistingEmail() throws Exception {
        when(userService.create(user)).thenThrow(new EntityExistsException());

        server.perform(post("/users/registration")
                        .flashAttr(USER_ATTRIBUTE_NAME, user))
                .andExpect(status().isOk())
                .andExpect(model().attribute(ERROR_MESSAGES_ATTRIBUTE_NAME, "Registration failed!"))
                .andExpect(view().name(containsString("users/registration")));

    }
}
