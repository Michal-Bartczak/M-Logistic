package pl.coderslab.magazyn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.magazyn.dto.UserRegistrationDTO;
import pl.coderslab.magazyn.repository.CustomerRepository;
import pl.coderslab.magazyn.repository.DriverRepository;
import pl.coderslab.magazyn.service.CustomerService;
import pl.coderslab.magazyn.service.DriverService;
import pl.coderslab.magazyn.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final DriverRepository driverRepository;
    private final CustomerRepository customerRepository;
    private final DriverService driverService;
    private final CustomerService customerService;
    private final UserService userService;

    public RegistrationController(DriverRepository driverRepository, CustomerRepository customerRepository, DriverService driverService, CustomerService customerService, UserService userService) {
        this.driverRepository = driverRepository;
        this.customerRepository = customerRepository;
        this.driverService = driverService;
        this.customerService = customerService;
        this.userService = userService;
    }

    @GetMapping("")
    public String showForm(Model model) {
        model.addAttribute("registrationForm", new UserRegistrationDTO());
        return "homepages/register";
    }

    @PostMapping("/driver")
    public String registerDriver(@Valid @ModelAttribute("registrationForm") UserRegistrationDTO userRegistrationDTO,
                                 BindingResult bindingResult,
                                 Model model) {
        if (!userService.checkExistEmailForAllUsers(userRegistrationDTO.getEmail())) {
            bindingResult.addError(new FieldError("registrationForm", "email", "Podany email jest już zajęty"));
        }
        if (!userService.checkExistUsernameForAllUsers(userRegistrationDTO.getUsername())) {
            bindingResult.addError(new FieldError("registrationForm", "username", "Podana nazwa użytkownika jest już zajęta"));
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("userType", "driver");
            return "homepages/register";
        }
        driverRepository.save(driverService.compereToDriver(userRegistrationDTO));
        return "redirect:/login?regi=true";
    }

    @PostMapping("/customer")
    public String registerCustomer(@Valid @ModelAttribute("registrationForm") UserRegistrationDTO userRegistrationDTO,
                                   BindingResult bindingResult,
                                   Model model) {
        if (!userService.checkExistEmailForAllUsers(userRegistrationDTO.getEmail())) {
            bindingResult.addError(new FieldError("registrationForm", "email", "Podany email jest już zajęty"));
        }
        if (!userService.checkExistUsernameForAllUsers(userRegistrationDTO.getUsername())) {
            bindingResult.addError(new FieldError("registrationForm", "username", "Podana nazwa użytkownika jest już zajęta"));
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("userType", "customer");
            return "homepages/register";
        }
        customerRepository.save(customerService.compereToCustomer(userRegistrationDTO));
        return "redirect:/login?regi=true";
    }
}




















