package pl.coderslab.magazyn.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.magazyn.entity.*;
import pl.coderslab.magazyn.exception.UnknownUserTypeException;
import pl.coderslab.magazyn.repository.AdminRepository;
import pl.coderslab.magazyn.repository.CustomerRepository;
import pl.coderslab.magazyn.repository.DriverRepository;
import pl.coderslab.magazyn.repository.EmployeeRepository;

import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final AdminRepository adminRepository;


    private final CustomerRepository customerRepository;


    private final DriverRepository driverRepository;

    private final EmployeeRepository employeeRepository;

    public CustomUserDetailsService(AdminRepository adminRepository, CustomerRepository customerRepository, DriverRepository driverRepository, EmployeeRepository employeeRepository) {
        this.adminRepository = adminRepository;
        this.customerRepository = customerRepository;
        this.driverRepository = driverRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BaseUser user = findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Użytkownik o takiej nazwie nie istnieje w bazie");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
    }

    public BaseUser findUserByUsername(String username) {
       BaseUser user;

        user = adminRepository.findByUsername(username);
        if (user != null) return user;

        user = customerRepository.findByUsername(username);
        if (user != null) return user;

        user = driverRepository.findByUsername(username);
        if (user != null) return user;

        user = employeeRepository.findByUsername(username);
        return user;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(BaseUser user) {
        String role;

        if (user instanceof Admin) {
            role = "ADMIN";
        } else if (user instanceof Customer) {
            role = "CUSTOMER";
        } else if (user instanceof Driver) {
            role = "DRIVER";
        } else if (user instanceof Employee) {
            role = "EMPLOYEE";
        } else {
            throw new UnknownUserTypeException("Rola użytkownika nie jest zdefiniowana");
        }

        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }
}
