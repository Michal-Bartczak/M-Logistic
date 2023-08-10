package pl.coderslab.magazyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.magazyn.RegistrationForm;
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
    public Customer compereToCustomer(RegistrationForm registrationForm){
        Customer customer = new Customer();
        customer.setUsername(registrationForm.getUsername());
        customer.setEmail(registrationForm.getEmail());
        customer.setPassword(registrationForm.getPassword());

        return userPasswordEncryptor.encryptPasswordInBaseUser(customer);
    }
}
