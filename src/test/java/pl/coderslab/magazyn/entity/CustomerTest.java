package pl.coderslab.magazyn.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import pl.coderslab.magazyn.repository.CustomerRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerTest {

    private Validator validator;
    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }

    @Test
    public void testValidDriver() {
        Customer customer = new Customer();
        customer.setUsername("customeruser");
        customer.setEmail("customer@example.com");
        customer.setPassword("password123");


        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(0, violations.size());
    }

    @Test
    public void testUsernameToShort() {
        Customer customer = new Customer();
        customer.setUsername("a");
        customer.setEmail("customer@example.com");
        customer.setPassword("password123");

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(1, violations.size());

    }

    @Test
    public void testDuplicateUsername() {
        Customer customer1 = new Customer();
        customer1.setUsername("customeruser");
        customer1.setEmail("customer2@example.com");
        customer1.setPassword("password123");

        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setUsername("customeruser");
        customer2.setEmail("customer2@example.com");
        customer2.setPassword("password123");

        when(customerRepository.save(customer2)).thenThrow(DataIntegrityViolationException.class);

        assertThrows(DataIntegrityViolationException.class, () -> customerRepository.save(customer2));

    }

    @Test
    public void testEmailNull() {
        Customer customer = new Customer();
        customer.setUsername("customeruser");
        customer.setEmail("customer@example.com");
        customer.setPassword("password123");


        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(1, violations.size());
    }

    @Test
    public void testDuplicateEmail() {
        Customer customer1 = new Customer();
        customer1.setUsername("customeruser1");
        customer1.setEmail("customer@example.com");
        customer1.setPassword("password123");

        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setUsername("customeruser2");
        customer2.setEmail("customer@example.com");
        customer2.setPassword("password123");

        when(customerRepository.save(customer2)).thenThrow(DataIntegrityViolationException.class);

        assertThrows(DataIntegrityViolationException.class, () -> customerRepository.save(customer2));

    }

    @Test
    public void testPasswordToShort() {
        Customer customer = new Customer();
        customer.setUsername("customeruser");
        customer.setEmail("customer@example.com");
        customer.setPassword("pass");


        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(1, violations.size());

    }

    @Test
    public void testValidOrders() {
        Customer customer = new Customer();
        customer.setUsername("customeruser");
        customer.setEmail("customer@example.com");
        customer.setPassword("password123");
        customer.setOrders(new ArrayList<>());

        Order order1 = new Order();
        order1.setStatus(OrderStatus.DOSTAWA);
        order1.setCustomer(customer);

        Order order2 = new Order();
        order2.setStatus(OrderStatus.DOSTAWA);
        order2.setCustomer(customer);

        customer.getOrders().add(order1);
        customer.getOrders().add(order2);

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        assertEquals(0, violations.size());
    }
}


