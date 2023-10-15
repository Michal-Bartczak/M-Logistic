package pl.coderslab.magazyn.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import pl.coderslab.magazyn.dto.UserRegistrationDTO;
import pl.coderslab.magazyn.entity.Customer;
import pl.coderslab.magazyn.entity.CustomerDetails;
import pl.coderslab.magazyn.entity.Order;
import pl.coderslab.magazyn.generic.UserPasswordEncryptor;
import pl.coderslab.magazyn.repository.CustomerRepository;
import pl.coderslab.magazyn.repository.OrderRepository;
import pl.coderslab.magazyn.service.CustomerService;
import pl.coderslab.magazyn.service.UserService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private UserPasswordEncryptor userPasswordEncryptor;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserService userService;
    @Mock
    private SecurityContext securityContext;

    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerRepository = Mockito.mock(CustomerRepository.class);
        userPasswordEncryptor = Mockito.mock(UserPasswordEncryptor.class);
        orderRepository = Mockito.mock(OrderRepository.class);
        userService = Mockito.mock(UserService.class);
        securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        customerService = new CustomerService(customerRepository, userPasswordEncryptor, orderRepository, userService);
    }

    @Test
    void testCompereToCustomer() {
        UserRegistrationDTO registrationForm = new UserRegistrationDTO();
        registrationForm.setUsername("testUser");
        registrationForm.setEmail("test@example.com");
        registrationForm.setPassword("testPassword");

        Customer expectedCustomer = new Customer();
        expectedCustomer.setUsername("testUser");
        expectedCustomer.setEmail("test@example.com");
        expectedCustomer.setPassword("testPassword");

        when(userPasswordEncryptor.encryptPasswordInBaseUser(Mockito.any(Customer.class))).thenReturn(expectedCustomer);

        Customer resultCustomer = customerService.compereToCustomer(registrationForm);

        assertEquals(expectedCustomer, resultCustomer);
    }

    @Test
    void testSaveCustomerRegistration() {
        Customer customer = new Customer();
        when(customerRepository.save(customer)).thenReturn(customer);

        assertDoesNotThrow(() -> customerService.saveCustomerRegistration(customer));
    }

    @Test
    void testGetCurrentUsernameForCustomer() {
        String username = "testUser";
        UserDetails userDetails = Mockito.mock(UserDetails.class);

        when(userDetails.getUsername()).thenReturn(username);

        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        assertEquals(username, customerService.getCurrentUsernameForCustomer());
    }

    @Test
    void testGetCurrentCustomerDetails() {
        String username = "testUser";
        Customer customer = new Customer();
        customer.setUsername(username);

        when(customerRepository.findByUsername(username)).thenReturn(customer);

        CustomerDetails customerDetails = new CustomerDetails();
        customer.setCustomerDetails(customerDetails);

        assertEquals(customerDetails, customerService.getCurrentCustomerDetails());
    }



    @Test
    void testGetAllCustomers() {
        List<Customer> customers = Collections.singletonList(new Customer());
        when(customerRepository.findAll()).thenReturn(customers);

        assertEquals(customers, customerService.getAllCustomers());
    }
}
