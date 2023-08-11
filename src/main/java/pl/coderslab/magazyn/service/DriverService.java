package pl.coderslab.magazyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.magazyn.dto.UserRegistrationDTO;
import pl.coderslab.magazyn.entity.Driver;
import pl.coderslab.magazyn.generic.UserPasswordEncryptor;
import pl.coderslab.magazyn.repository.DriverRepository;
@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final UserPasswordEncryptor userPasswordEncryptor;

    @Autowired
    public DriverService(DriverRepository driverRepository, UserPasswordEncryptor userPasswordEncryptor){
        this.driverRepository = driverRepository;
        this.userPasswordEncryptor = userPasswordEncryptor;
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
}
