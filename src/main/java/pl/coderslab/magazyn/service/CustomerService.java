package pl.coderslab.magazyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.coderslab.magazyn.dto.UserRegistrationDTO;
import pl.coderslab.magazyn.entity.Customer;
import pl.coderslab.magazyn.entity.CustomerDetails;
import pl.coderslab.magazyn.entity.Order;
import pl.coderslab.magazyn.generic.UserPasswordEncryptor;
import pl.coderslab.magazyn.repository.CustomerRepository;
import pl.coderslab.magazyn.repository.OrderRepository;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserPasswordEncryptor userPasswordEncryptor;
   private final OrderRepository orderRepository;



    @Autowired
    public CustomerService(CustomerRepository customerRepository, UserPasswordEncryptor userPasswordEncryptor, OrderRepository orderRepository){
        this.customerRepository=customerRepository;
        this.userPasswordEncryptor = userPasswordEncryptor;


        this.orderRepository = orderRepository;
    }
    public Customer compereToCustomer(UserRegistrationDTO registrationForm){
        Customer customer = new Customer();
        customer.setUsername(registrationForm.getUsername());
        customer.setEmail(registrationForm.getEmail());
        customer.setPassword(registrationForm.getPassword());

        return userPasswordEncryptor.encryptPasswordInBaseUser(customer);
    }
    public void saveCustomerRegistration(Customer customer){
        customerRepository.save(customer);
    }
    public String getCurrentUsernameForCustomer() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    public CustomerDetails getCurrentCustomerDetails(){
    Customer customer = customerRepository.findByUsername(getCurrentUsernameForCustomer());
            CustomerDetails customerDetails = customer.getCustomerDetails();
            if (customerDetails == null){
                return new CustomerDetails();
            }
            return customerDetails;

    }
    public Customer getCurrentCustomerObject(){
        return customerRepository.findByUsername(getCurrentUsernameForCustomer());
    }

    public void addSendPackageCustomer(Order order) {
        Customer currentCustomer = getCurrentCustomerObject(); // Pobieranie aktualnie zalogowanego klienta
        order.setCustomer(currentCustomer);  // Ustawianie klienta dla zamówienia

        orderRepository.save(order);  // Zapisywanie zamówienia w bazie danych
    }


}

