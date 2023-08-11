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

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("registrationForm", new UserRegistrationDTO());
        return "homepages/register";
    }

    @PostMapping("/driver")
    public String registerDriver(@ModelAttribute UserRegistrationDTO registrationForm,
                                 BindingResult bindingResult,
                                 Model model) {
        if (!userService.checkExistEmailForAllUsers(registrationForm.getEmail())) {
            bindingResult.addError(new FieldError("registrationForm", "email", "Podany email jest już zajęty"));
        }
        if (!userService.checkExistUsernameForAllUsers(registrationForm.getUsername())) {
            bindingResult.addError(new FieldError("registrationForm", "username", "Podana nazwa użytkownika jest już zajęta"));
        }
        if (bindingResult.hasErrors()) {
            return "homepages/register";
        }
        driverRepository.save(driverService.compereToDriver(registrationForm));
        return "redirect:/login?registered=true";
    }

    @PostMapping("/customer")
    public String registerCustomer(@ModelAttribute UserRegistrationDTO registrationForm,
                                   BindingResult bindingResult,
                                   Model model) {
        if (!userService.checkExistEmailForAllUsers(registrationForm.getEmail())) {
            bindingResult.addError(new FieldError("registrationForm", "email", "Podany email jest już zajęty"));
        }
        if (!userService.checkExistUsernameForAllUsers(registrationForm.getUsername())) {
            bindingResult.addError(new FieldError("registrationForm", "username", "Podana nazwa użytkownika jest już zajęta"));
        }
        if (bindingResult.hasErrors()) {
            return "homepages/register";
        }
        customerRepository.save(customerService.compereToCustomer(registrationForm));
        return "redirect:/login?registered=true";
    }
}