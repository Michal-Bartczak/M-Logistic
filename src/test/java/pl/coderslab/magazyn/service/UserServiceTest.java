package pl.coderslab.magazyn.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.coderslab.magazyn.entity.Driver;
import pl.coderslab.magazyn.repository.AdminRepository;
import pl.coderslab.magazyn.repository.CustomerRepository;
import pl.coderslab.magazyn.repository.DriverRepository;
import pl.coderslab.magazyn.repository.EmployeeRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    @Mock
    private AdminRepository adminRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private DriverRepository driverRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCheckExistEmailForAllUsers() {
        when(adminRepository.findByEmail("test@example.com")).thenReturn(null);
        when(employeeRepository.findByEmail("test@example.com")).thenReturn(null);
        when(driverRepository.findByEmail("test@example.com")).thenReturn(null);
        when(customerRepository.findByEmail("test@example.com")).thenReturn(null);

        boolean result = userService.checkExistEmailForAllUsers("test@example.com");

        assertTrue(result);
    }
    @Test
    public void testCheckExistUsernameForAllUsers(){
        when(adminRepository.findByUsername("username")).thenReturn(null);
        when(employeeRepository.findByUsername("username")).thenReturn(null);
        when(driverRepository.findByUsername("username")).thenReturn(null);
        when(customerRepository.findByUsername("username")).thenReturn(null);

        boolean result = userService.checkExistEmailForAllUsers("username");

        assertTrue(result);
    }
    @Test
    public void testIsPasswordValid() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserService userService = new UserService(customerRepository, employeeRepository, adminRepository, driverRepository, passwordEncoder);

        String rawPassword = "myPassword";
        String hashedPassword = passwordEncoder.encode(rawPassword); // Haszujemy hasło

        boolean result = userService.isPasswordValid(rawPassword, hashedPassword);

        assertTrue(result);
    }
}
