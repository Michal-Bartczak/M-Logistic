package pl.coderslab.magazyn.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import pl.coderslab.magazyn.repository.DriverRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DriverTest {

    private Validator validator;
    @Mock
    private DriverRepository driverRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }

    @Test
    public void testValidDriver() {
        Driver driver = new Driver();
        driver.setUsername("driveruser");
        driver.setEmail("driver@example.com");
        driver.setPassword("password123");
        driver.setName("name");

        Set<ConstraintViolation<Driver>> violations = validator.validate(driver);
        assertEquals(0, violations.size());
    }

    @Test
    public void testUsernameToShort() {
        Driver driver = new Driver();
        driver.setUsername("a");   // username to short
        driver.setEmail("driver@example.com");
        driver.setPassword("password123");

        Set<ConstraintViolation<Driver>> violations = validator.validate(driver);
        assertEquals(1, violations.size());

    }

    @Test
    public void testDuplicateUsername() {
        Driver driver1 = new Driver();
        driver1.setUsername("driver1");
        driver1.setEmail("test@example.com");
        driver1.setPassword("password123");
        driverRepository.save(driver1);

        Driver driver2 = new Driver();
        driver2.setUsername("driver1");   // Duplicate username
        driver2.setEmail("test1@example.com");
        driver2.setPassword("password456");

        when(driverRepository.save(driver2)).thenThrow(DataIntegrityViolationException.class);

        assertThrows(DataIntegrityViolationException.class, () -> driverRepository.save(driver2));

    }

    @Test
    public void testEmailNull() {
        Driver driver = new Driver();
        driver.setUsername("admin1");
        driver.setPassword("password123");

        Set<ConstraintViolation<Driver>> violations = validator.validate(driver);
        assertEquals(1, violations.size());
    }

    @Test
    public void testDuplicateEmail() {
        Driver driver1 = new Driver();
        driver1.setUsername("driver1");
        driver1.setEmail("duplicate@example.com");
        driver1.setPassword("password123");
        driverRepository.save(driver1);

        Driver driver2 = new Driver();
        driver2.setUsername("driver2");
        driver2.setEmail("duplicate@example.com");  // Duplicate email
        driver2.setPassword("password456");

        when(driverRepository.save(driver2)).thenThrow(DataIntegrityViolationException.class);

        assertThrows(DataIntegrityViolationException.class, () -> driverRepository.save(driver2));

    }

    @Test
    public void testPasswordToShort() {
        Driver driver = new Driver();
        driver.setUsername("driver");
        driver.setEmail("duplicate@example.com");
        driver.setPassword("pass");

        Set<ConstraintViolation<Driver>> violations = validator.validate(driver);
        assertEquals(1, violations.size());

    }

    @Test
    public void testNameToShort() {
        Driver driver = new Driver();
        driver.setUsername("username");
        driver.setPassword("password");
        driver.setEmail("email@gmail.com");
        driver.setName("aa");

        Set<ConstraintViolation<Driver>> violations = validator.validate(driver);
        assertEquals(1, violations.size());
    }

    @Test
    public void testSurnameToShort() {
        Driver driver = new Driver();
        driver.setUsername("username");
        driver.setPassword("password");
        driver.setEmail("email@gmail.com");
        driver.setName("name");
        driver.setSurname("aa");

        Set<ConstraintViolation<Driver>> violations = validator.validate(driver);
        assertEquals(1, violations.size());
    }

    @Test
    public void testSurnameToLong() {
        Driver driver = new Driver();
        driver.setUsername("username");
        driver.setPassword("password");
        driver.setEmail("email@gmail.com");
        driver.setName("name");
        driver.setSurname("12345,12345,12345");

        Set<ConstraintViolation<Driver>> violations = validator.validate(driver);
        assertEquals(1, violations.size());
    }

    @Test
    public void testValidLogs() {
        Driver driver = new Driver();
        driver.setUsername("driveruser");
        driver.setEmail("driver@example.com");
        driver.setPassword("password123");
        driver.setLogs(new ArrayList<>());

        DeliveryLog log1 = new DeliveryLog();
        log1.setDriver(driver);
        log1.setDeliveryStatus(DeliveryStatus.DOSTARCZONO);
        log1.setDeliveryDate(LocalDate.now());
        driver.addLog(log1);

        DeliveryLog log2 = new DeliveryLog();
        log1.setDriver(driver);
        log1.setDeliveryStatus(DeliveryStatus.DOSTARCZONO);
        log1.setDeliveryDate(LocalDate.now());
        driver.addLog(log2);

        Set<ConstraintViolation<Driver>> violations = validator.validate(driver);
        assertEquals(0, violations.size());
    }
}
