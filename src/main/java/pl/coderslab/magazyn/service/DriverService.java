package pl.coderslab.magazyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.coderslab.magazyn.dto.UserRegistrationDTO;
import pl.coderslab.magazyn.entity.Driver;
import pl.coderslab.magazyn.entity.Order;
import pl.coderslab.magazyn.entity.OrderStatus;
import pl.coderslab.magazyn.generic.UserPasswordEncryptor;
import pl.coderslab.magazyn.repository.DriverRepository;
import pl.coderslab.magazyn.repository.OrderRepository;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final UserPasswordEncryptor userPasswordEncryptor;
    private final OrderRepository orderRepository;
    private final UserService userService;

    @Autowired
    public DriverService(DriverRepository driverRepository, UserPasswordEncryptor userPasswordEncryptor, OrderRepository orderRepository, UserService userService){
        this.driverRepository = driverRepository;
        this.userPasswordEncryptor = userPasswordEncryptor;
        this.orderRepository = orderRepository;
        this.userService = userService;
    }
    public Driver compereToDriver(UserRegistrationDTO registrationForm){
        Driver driver = new Driver();
        driver.setUsername(registrationForm.getUsername());
        driver.setEmail(registrationForm.getEmail());
        driver.setPassword(registrationForm.getPassword());
        driver.setName(registrationForm.getName());
        driver.setSurname(registrationForm.getSurname());
        return userPasswordEncryptor.encryptPasswordInBaseUser(driver);

    }
    public void saveDriverRegistration(Driver driver){
        driverRepository.save(driver);
    }
    public String getCurrentUsernameForDriver(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
    public Driver getCurrentDriverObject(){
       return driverRepository.findByUsername(getCurrentUsernameForDriver());
    }
    public List<Order> getAllOrdersForCurrentDriver(){
      return orderRepository.findByProviderAndStatus(getCurrentDriverObject().getUsername(), OrderStatus.DOSTAWA);
    }
    public boolean saveNewPasswordDriver(String oldPassword, String newPassword){
        Driver driver = getCurrentDriverObject();
        if (userService.isPasswordValid(oldPassword, driver.getPassword())){
            driver.setPassword(newPassword);
            userPasswordEncryptor.encryptPasswordInBaseUser(driver);
            driverRepository.save(driver);
            return true;
        }
        return false;
    }
    public List<Driver> getAllDrivers(){
        return driverRepository.findAll();
    }
}
