package pl.coderslab.magazyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.magazyn.entity.*;
import pl.coderslab.magazyn.repository.AdminRepository;
import pl.coderslab.magazyn.repository.CustomerRepository;
import pl.coderslab.magazyn.repository.DriverRepository;
import pl.coderslab.magazyn.repository.EmployeeRepository;

import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BaseUser user = findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
    }

    private BaseUser findUserByUsername(String username) {
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
            throw new IllegalArgumentException("Unknown user type");
        }

        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }
}
