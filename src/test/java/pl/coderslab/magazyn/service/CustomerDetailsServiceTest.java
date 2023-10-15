package pl.coderslab.magazyn.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.coderslab.magazyn.entity.Customer;
import pl.coderslab.magazyn.entity.CustomerDetails;
import pl.coderslab.magazyn.repository.CustomerDetailsRepository;
import pl.coderslab.magazyn.repository.CustomerRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CustomerDetailsServiceTest {

    private CustomerDetailsService customerDetailsService;
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        CustomerDetailsRepository customerDetailsRepository = Mockito.mock(CustomerDetailsRepository.class);
        customerRepository = Mockito.mock(CustomerRepository.class);
        customerDetailsService = new CustomerDetailsService(customerDetailsRepository, customerRepository, null);

    }

    @Test
    public void testAddOrUpdateWhenExistCustomerDetails() {

        Customer customer = new Customer();
        CustomerDetails existingDetails = new CustomerDetails();
        customer.setCustomerDetails(existingDetails);
        CustomerDetails newDetails = new CustomerDetails();

        customerDetailsService.addOrUpdateWhenExistCustomerDetails(customer, newDetails);

        verify(customerRepository, times(1)).save(customer);
        assertEquals(newDetails, customer.getCustomerDetails());
    }

    @Test
    public void testAddOrUpdateWhenNotExistCustomerDetails() {

        Customer customer = new Customer();
        CustomerDetails newDetails = new CustomerDetails();

        customerDetailsService.addOrUpdateWhenExistCustomerDetails(customer, newDetails);

        verify(customerRepository, times(1)).save(customer);
        assertEquals(newDetails, customer.getCustomerDetails());
    }
}
