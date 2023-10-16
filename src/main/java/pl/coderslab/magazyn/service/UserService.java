package pl.coderslab.magazyn.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.magazyn.repository.AdminRepository;
import pl.coderslab.magazyn.repository.CustomerRepository;
import pl.coderslab.magazyn.repository.DriverRepository;
import pl.coderslab.magazyn.repository.EmployeeRepository;

import java.util.Objects;
import java.util.stream.Stream;

@Service
public class UserService {
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final AdminRepository adminRepository;
    private final DriverRepository driverRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(CustomerRepository customerRepository, EmployeeRepository employeeRepository, AdminRepository adminRepository, DriverRepository driverRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.adminRepository = adminRepository;
        this.driverRepository = driverRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean checkExistEmailForAllUsers(String email) {
        return Stream.of(
                        adminRepository.findByEmail(email),
                        employeeRepository.findByEmail(email),
                        driverRepository.findByEmail(email),
                        customerRepository.findByEmail(email))
                .allMatch(Objects::isNull);
    }

    public boolean checkExistUsernameForAllUsers(String username){
            return Stream.of(
                    adminRepository.findByUsername(username),
                    employeeRepository.findByUsername(username),
                    driverRepository.findByUsername(username),
                    customerRepository.findByUsername(username))
                            .allMatch(Objects::isNull);
    }
    public boolean isPasswordValid(String rawPassword, String hashedPassword) {
        if (hashedPassword == null)
            return false;
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }


}
