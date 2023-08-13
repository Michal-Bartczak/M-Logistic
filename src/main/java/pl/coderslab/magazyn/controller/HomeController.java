package pl.coderslab.magazyn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.magazyn.entity.Employee;
import pl.coderslab.magazyn.generic.UserPasswordEncryptor;
import pl.coderslab.magazyn.repository.EmployeeRepository;

@Controller

public class HomeController {

 private final UserPasswordEncryptor userPasswordEncryptor;
 private final EmployeeRepository employeeRepository;

    public HomeController(UserPasswordEncryptor userPasswordEncryptor, EmployeeRepository employeeRepository) {
        this.userPasswordEncryptor = userPasswordEncryptor;
        this.employeeRepository = employeeRepository;
    }


    @GetMapping("/")
    public String hello(){
        return "homepages/homePage";
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

}
