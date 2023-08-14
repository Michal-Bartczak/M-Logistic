package pl.coderslab.magazyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.magazyn.entity.Customer;
import pl.coderslab.magazyn.entity.CustomerDetails;
import pl.coderslab.magazyn.repository.CustomerDetailsRepository;
import pl.coderslab.magazyn.repository.CustomerRepository;

@Service
public class CustomerDetailsService {
    private final CustomerDetailsRepository customerDetailsRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerDetailsService(CustomerDetailsRepository customerDetailsRepository, CustomerRepository customerRepository) {
        this.customerDetailsRepository = customerDetailsRepository;
        this.customerRepository = customerRepository;
    }

    public void addOrUpdateWhenExistCustomerDetails(Customer customer, CustomerDetails customerDetails) {
        if (customer.getCustomerDetails() == null) {
            customer.setCustomerDetails(customerDetails);
            customerDetails.setCustomer(customer);
        }
        Long id = customer.getCustomerDetails().getId();
        customerDetails.setId(id);
        customer.setCustomerDetails(customerDetails);
        customerDetails.setCustomer(customer);
        customerRepository.save(customer);

    }

}
