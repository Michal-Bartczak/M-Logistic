package pl.coderslab.magazyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.coderslab.magazyn.dto.UserRegistrationDTO;
import pl.coderslab.magazyn.entity.Customer;
import pl.coderslab.magazyn.generic.UserPasswordEncryptor;
import pl.coderslab.magazyn.repository.CustomerRepository;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserPasswordEncryptor userPasswordEncryptor;
    @Autowired
    public CustomerService(CustomerRepository customerRepository, UserPasswordEncryptor userPasswordEncryptor){
        this.customerRepository=customerRepository;
        this.userPasswordEncryptor = userPasswordEncryptor;
    }
    public Customer compereToCustomer(UserRegistrationDTO registrationForm){
        Customer customer = new Customer();
        customer.setUsername(registrationForm.getUsername());
        customer.setEmail(registrationForm.getEmail());
        customer.setPassword(registrationForm.getPassword());

        return userPasswordEncryptor.encryptPasswordInBaseUser(customer);
    }
    public String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
