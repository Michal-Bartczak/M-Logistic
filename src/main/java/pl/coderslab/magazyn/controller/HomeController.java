package pl.coderslab.magazyn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.magazyn.entity.Admin;
import pl.coderslab.magazyn.entity.Employee;
import pl.coderslab.magazyn.exception.OrderNotFoundException;
import pl.coderslab.magazyn.generic.UserPasswordEncryptor;
import pl.coderslab.magazyn.repository.AdminRepository;
import pl.coderslab.magazyn.repository.EmployeeRepository;

@Controller

public class HomeController {

 private final UserPasswordEncryptor userPasswordEncryptor;
 private final EmployeeRepository employeeRepository;
 private final AdminRepository adminRepository;

    public HomeController(UserPasswordEncryptor userPasswordEncryptor, EmployeeRepository employeeRepository, AdminRepository adminRepository) {
        this.userPasswordEncryptor = userPasswordEncryptor;
        this.employeeRepository = employeeRepository;
        this.adminRepository = adminRepository;
    }


    @GetMapping("/")
    public String getHomePage(){
        return "homepages/homePage";
    }

    @GetMapping("/contact")
    public String getContact(){
        return "/homepages/contact";
    }

    @GetMapping("/159406")
    public String add(Model model){
        model.addAttribute("form", new Employee());
        return "/homepages/addEmployeeForm";
    }
    @PostMapping("/159406")
    public String  saveAll(Employee employee){
       userPasswordEncryptor.encryptPasswordInBaseUser(employee);
       employeeRepository.save(employee);
       return "redirect:/159406";
    }
    @GetMapping("/159406111")
    public String getRegisterFormAdmins(){
        return "/homepages/addAdmins";
    }
    @PostMapping("/159406111")
    public String  saveAllAdmins(Admin admin){
        userPasswordEncryptor.encryptPasswordInBaseUser(admin);
     adminRepository.save(admin);
        return "redirect:/159406111";
    }
    @GetMapping("/someEndpoint")
    public OrderNotFoundException myControllerMethod() {
        return new OrderNotFoundException("Nie można znaleźć");
    }
    @GetMapping("/throwException")
    public String throwException() {
        throw new OrderNotFoundException("Testowy wyjątek OrderNotFoundException");
    }
}
