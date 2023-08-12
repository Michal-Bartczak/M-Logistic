package pl.coderslab.magazyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.magazyn.entity.Customer;
import pl.coderslab.magazyn.entity.CustomerDetails;
import pl.coderslab.magazyn.repository.CustomerDetailsRepository;

@Service
public class CustomerDetailsService {
    private final CustomerDetailsRepository customerDetailsRepository;

    @Autowired
    public CustomerDetailsService(CustomerDetailsRepository customerDetailsRepository) {
        this.customerDetailsRepository = customerDetailsRepository;
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
    }

}
