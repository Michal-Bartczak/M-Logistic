package pl.coderslab.magazyn.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import pl.coderslab.magazyn.dto.UserRegistrationDTO;
import pl.coderslab.magazyn.entity.Driver;
import pl.coderslab.magazyn.entity.OrderStatus;
import pl.coderslab.magazyn.generic.UserPasswordEncryptor;
import pl.coderslab.magazyn.repository.DeliveryLogRepository;
import pl.coderslab.magazyn.repository.DriverRepository;
import pl.coderslab.magazyn.repository.OrderRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Test
    public void testSaveDriverRegistration() {
        Driver driver = new Driver();
        driver.setUsername("test");
        driver.setEmail("test");
        driver.setPassword("test");
        driver.setName("user");

        driverService.saveDriverRegistration(driver);
        verify(driverRepository, Mockito.times(1)).save(driver);
    }

    @Test
    public void testGetCurrentUsernameForDriver() {
        User user = new User("testUser", "password", Collections.emptyList());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        String username = driverService.getCurrentUsernameForDriver();

        assertEquals(username, "testUser");
    }

    @Test
    public void testGetCurrentDriverObject() {

        Driver testDriver = new Driver();
        testDriver.setUsername("testUser");
        Mockito.when(driverRepository.findByUsername("testUser")).thenReturn(testDriver);

        SecurityContextHolder.getContext().setAuthentication(createTestAuthentication());

        Driver result = driverService.getCurrentDriverObject();

        assertEquals(testDriver, result);

    }

    @Test
    public void testGetAllOrdersForCurrentDriver() {

        Driver driver = new Driver();
        driver.setUsername("testUser");
        Mockito.when(driverRepository.findByUsername("testUser")).thenReturn(driver);

        SecurityContextHolder.getContext().setAuthentication(createTestAuthentication());

        driverService.getAllOrdersForCurrentDriver();


        Mockito.verify(orderRepository, times(1))
                .findByProviderAndStatus(Mockito.anyString(), Mockito.any(OrderStatus.class));
    }

    @Test
    public void testSaveNewPasswordDriver() {
        Driver driver = new Driver();
        driver.setUsername("testUser");
        driver.setPassword("password");
        Mockito.when(driverRepository.findByUsername("testUser")).thenReturn(driver);
        Mockito.when(userService.isPasswordValid("password", "password")).thenReturn(true);

        SecurityContextHolder.getContext().setAuthentication(createTestAuthentication());

        boolean result = driverService.saveNewPasswordDriver("password", "newPassword");

        assertTrue(result);
        assertEquals("newPassword", driver.getPassword());

        Mockito.verify(userService, Mockito.times(1)).isPasswordValid("password", "password");
        Mockito.verify(userPasswordEncryptor, Mockito.times(1)).encryptPasswordInBaseUser(driver);
        Mockito.verify(driverRepository, Mockito.times(1)).save(driver);

    }
    @Test
    public void testGetAllDrivers(){
        List<Driver> sampleDriverList = new ArrayList<>();
        Driver driver = new Driver();
        driver.setUsername("driver");
        Driver driver1 = new Driver();
        driver1.setUsername("driver1");
        sampleDriverList.add(driver1);
        sampleDriverList.add(driver);

        when(driverRepository.findAll()).thenReturn(sampleDriverList);

        List<Driver> sampleList = driverService.getAllDrivers();

        assertEquals(sampleList, sampleDriverList);
        verify(driverRepository, times(1)).findAll();
    }

    private Authentication createTestAuthentication() {
        User testUser = new User("testUser", "password", Collections.emptyList());
        return new UsernamePasswordAuthenticationToken(testUser, "password", testUser.getAuthorities());
    }
}












