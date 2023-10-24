package pl.coderslab.magazyn.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import pl.coderslab.magazyn.entity.Customer;
import pl.coderslab.magazyn.entity.Driver;
import pl.coderslab.magazyn.entity.Employee;
import pl.coderslab.magazyn.entity.Order;
import pl.coderslab.magazyn.repository.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AdminServiceTest {



    private AdminService adminService;

    private AdminRepository adminRepository;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private DriverRepository driverRepository;
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() {
        adminRepository = Mockito.mock(AdminRepository.class);
        orderRepository = Mockito.mock(OrderRepository.class);
        customerRepository = Mockito.mock(CustomerRepository.class);
        driverRepository = Mockito.mock(DriverRepository.class);
        employeeRepository = Mockito.mock(EmployeeRepository.class);

        adminService = new AdminService(adminRepository, orderRepository, customerRepository, driverRepository, employeeRepository);
    }

    @Test
    public void testGetCurrentUsernameForAdmin() {
        // Symulacja autentykacji
        String username = "admin";
        Authentication authentication = new UsernamePasswordAuthenticationToken(new User(username, "password", new ArrayList<>()), null);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        String result = adminService.getCurrentUsernameForAdmin();

        // Sprawdź, czy metoda zwraca poprawną nazwę użytkownika
        assertEquals(username, result);
    }



    @Test
    public void testGetAllOrdersSortedByStatus() {
        // Symulacja zachowania repozytorium
        List<Order> orders = new ArrayList<>();
        when(orderRepository.findAllSortedByStatusAndProvider()).thenReturn(orders);

        // Wywołaj metodę getAllOrdersSortedByStatus
        List<Order> result = adminService.getAllOrdersSortedByStatus();

        // Sprawdź, czy metoda zwraca poprawną listę zamówień
        assertEquals(orders, result);
    }

    @Test
    public void testGetAllCustomersSortedById() {
        // Symulacja zachowania repozytorium
        List<Customer> customers = new ArrayList<>();
        when(customerRepository.findAllByOrderByIdAsc()).thenReturn(customers);

        // Wywołaj metodę getAllCustomersSortedById
        List<Customer> result = adminService.getAllCustomersSortedById();

        // Sprawdź, czy metoda zwraca poprawną listę klientów
        assertEquals(customers, result);
    }

    @Test
    public void testGetAllDrivers() {
        // Symulacja zachowania repozytorium
        List<Driver> drivers = new ArrayList<>();
        when(driverRepository.findAll()).thenReturn(drivers);

        // Wywołaj metodę getAllDrivers
        List<Driver> result = adminService.getAllDrivers();

        // Sprawdź, czy metoda zwraca poprawną listę kierowców
        assertEquals(drivers, result);
    }

    @Test
    public void testGetAllEmployee() {
        // Symulacja zachowania repozytorium
        List<Employee> employees = new ArrayList<>();
        when(employeeRepository.findAll()).thenReturn(employees);

        // Wywołaj metodę getAllEmployee
        List<Employee> result = adminService.getAllEmployee();

        // Sprawdź, czy metoda zwraca poprawną listę pracowników
        assertEquals(employees, result);
    }
}
