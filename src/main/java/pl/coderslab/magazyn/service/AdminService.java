package pl.coderslab.magazyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.coderslab.magazyn.entity.*;
import pl.coderslab.magazyn.repository.*;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
   private final DriverRepository driverRepository;
   private final EmployeeRepository employeeRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, OrderRepository orderRepository, CustomerRepository customerRepository, DriverRepository driverRepository, EmployeeRepository employeeRepository) {
        this.adminRepository = adminRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.driverRepository = driverRepository;
        this.employeeRepository = employeeRepository;
    }

    public String getCurrentUsernameForAdmin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    public Admin getCurrentAdminObject(){
        return adminRepository.findByUsername(getCurrentUsernameForAdmin());
    }
    public List<Order> getAllOrdersSortedByStatus(){
        return orderRepository.findAllSortedByStatusAndProvider();
    }
    public List<Customer> getAllCustomersSortedById(){
        return customerRepository.findAllByOrderByIdAsc();
    }
    public List<Driver> getAllDrivers(){
        return driverRepository.findAll();
    }
    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

}
