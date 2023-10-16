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
import pl.coderslab.magazyn.entity.Driver;
import pl.coderslab.magazyn.entity.Employee;
import pl.coderslab.magazyn.generic.UserPasswordEncryptor;
import pl.coderslab.magazyn.repository.DriverRepository;
import pl.coderslab.magazyn.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private UserService userService;
    @Mock
    private UserPasswordEncryptor userPasswordEncryptor;
    @Mock
    private DriverRepository driverRepository;
    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCurrentUsernameForEmployee() {

        User user = new User("testUser", "password", Collections.emptyList());

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String username = employeeService.getCurrentUsernameForEmployee();

        assertEquals("testUser", username);
    }

    @Test
    void testGetCurrentEmployeeObject() {

        Employee testEmployee = new Employee();
        testEmployee.setUsername("testUser");
        Mockito.when(employeeRepository.findByUsername("testUser")).thenReturn(testEmployee);

        SecurityContextHolder.getContext().setAuthentication(createTestAuthentication());

        Employee result = employeeService.getCurrentEmployeeObject();

        assertEquals(testEmployee, result);
    }

    private Authentication createTestAuthentication() {
        User testUser = new User("testUser", "password", Collections.emptyList());
        return new UsernamePasswordAuthenticationToken(testUser, "password", testUser.getAuthorities());
    }

    @Test
    public void testSaveNewPasswordEmployee() {
        Employee testEmployee = new Employee();
        testEmployee.setUsername("testUser");
        testEmployee.setPassword("password");
        Mockito.when(employeeRepository.findByUsername("testUser")).thenReturn(testEmployee);
        Mockito.when(userService.isPasswordValid("password", "password")).thenReturn(true);

        SecurityContextHolder.getContext().setAuthentication(createTestAuthentication());

        boolean result = employeeService.saveNewPasswordEmployee("password", "newPassword");

        assertTrue(result);
        assertEquals("newPassword", testEmployee.getPassword());

        Mockito.verify(userService, Mockito.times(1)).isPasswordValid("password", "password");
        Mockito.verify(userPasswordEncryptor, Mockito.times(1)).encryptPasswordInBaseUser(testEmployee);
        Mockito.verify(employeeRepository, Mockito.times(1)).save(testEmployee);
    }

    @Test
    public void testFindAllEmployees() {
        List<Employee> testList = new ArrayList<>();
        Mockito.when(employeeRepository.findAll()).thenReturn(testList);
        List<Employee> result = (employeeService.findAllEmployees());

        Mockito.verify(employeeRepository, Mockito.times(1)).findAll();

        assertEquals(testList, result);
    }
    @Test
    public void testFindAllDrivers(){
        List<Driver> testList = new ArrayList<>();
        Mockito.when(driverRepository.findAll()).thenReturn(testList);

        List<Driver> result = employeeService.findAllDrivers();

        Mockito.verify(driverRepository, Mockito.times(1)).findAll();

        assertEquals(testList,result);

    }
}





