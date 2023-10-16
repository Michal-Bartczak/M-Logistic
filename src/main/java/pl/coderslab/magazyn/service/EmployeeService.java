package pl.coderslab.magazyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.coderslab.magazyn.entity.Driver;
import pl.coderslab.magazyn.entity.Employee;
import pl.coderslab.magazyn.generic.UserPasswordEncryptor;
import pl.coderslab.magazyn.repository.DriverRepository;
import pl.coderslab.magazyn.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserService userService;
    private final UserPasswordEncryptor userPasswordEncryptor;
    private final DriverRepository driverRepository;


    public EmployeeService(EmployeeRepository employeeRepository, UserService userService,
                           UserPasswordEncryptor userPasswordEncryptor, DriverRepository driverRepository) {
        this.employeeRepository = employeeRepository;
        this.userService = userService;
        this.userPasswordEncryptor = userPasswordEncryptor;
        this.driverRepository = driverRepository;
    }

    public String getCurrentUsernameForEmployee() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
    public Employee getCurrentEmployeeObject(){
        return employeeRepository.findByUsername(getCurrentUsernameForEmployee());
    }

    public boolean saveNewPasswordEmployee(String oldPassword, String newPassword){
        Employee employee = getCurrentEmployeeObject();
        if (userService.isPasswordValid(oldPassword, employee.getPassword())){
            employee.setPassword(newPassword);
            userPasswordEncryptor.encryptPasswordInBaseUser(employee);
            employeeRepository.save(employee);
            return true;
        }
        return false;
    }

    public List<Employee> findAllEmployees(){
      return employeeRepository.findAll();

    }
    public List<Driver> findAllDrivers(){
        return driverRepository.findAll();
    }
}
