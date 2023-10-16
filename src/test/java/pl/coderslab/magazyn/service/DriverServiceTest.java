package pl.coderslab.magazyn.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.coderslab.magazyn.dto.UserRegistrationDTO;
import pl.coderslab.magazyn.entity.Driver;
import pl.coderslab.magazyn.generic.UserPasswordEncryptor;
import pl.coderslab.magazyn.repository.DeliveryLogRepository;
import pl.coderslab.magazyn.repository.DriverRepository;
import pl.coderslab.magazyn.repository.OrderRepository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DriverServiceTest {
    @Mock
    private DriverRepository driverRepository;
    @Mock
    private UserPasswordEncryptor userPasswordEncryptor;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserService userService;
    @Mock
    private DeliveryLogRepository deliveryLogRepository;
    @InjectMocks
    private DriverService driverService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCompereToDriver() {
        UserRegistrationDTO dto = new UserRegistrationDTO();
        dto.setUsername("testuser");
        dto.setEmail("test@gmail.com");
        dto.setPassword("testuser");
        dto.setName("user");
        dto.setSurname("test");

        Driver testDriver = new Driver();
        testDriver.setUsername("testuser");
        testDriver.setEmail("test@gmail.com");
        testDriver.setPassword("testuser");
        testDriver.setName("user");
        testDriver.setSurname("test");


        testDriver = userPasswordEncryptor.encryptPasswordInBaseUser(testDriver);

        Driver resultDriver = driverService.compereToDriver(dto);


        assertEquals(testDriver, resultDriver);


    }


}












