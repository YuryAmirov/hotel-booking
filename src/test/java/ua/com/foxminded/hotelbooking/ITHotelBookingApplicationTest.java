package ua.com.foxminded.hotelbooking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HotelBookingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITHotelBookingApplicationTest {

    @Test
    public void whenApplicationsMainMethodInvokedThenApplicationStartsSuccessfully() {
        HotelBookingApplication.main(new String[]{});
    }

}
