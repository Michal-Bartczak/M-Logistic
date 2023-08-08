package pl.coderslab.magazyn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.magazyn.RegistrationForm;
import pl.coderslab.magazyn.entity.Customer;
import pl.coderslab.magazyn.entity.Driver;
import pl.coderslab.magazyn.repository.CustomerRepository;
import pl.coderslab.magazyn.repository.DriverRepository;
import pl.coderslab.magazyn.service.CustomerService;
import pl.coderslab.magazyn.service.DriverService;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DriverService driverService;
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processForm(@RequestParam("userType") String userType,
                              @ModelAttribute RegistrationForm registrationForm,
                              BindingResult bindingResult) {
        if ("driver".equals(userType)) {
            if (bindingResult.hasErrors()) {
                return "register";
            }
            driverRepository.save(driverService.compereToDriver(registrationForm));
            return "/login";
        } else if ("customer".equals(userType)) {

            if (bindingResult.hasErrors()) {
                return "register";
            }

            customerRepository.save(customerService.compereToCustomer(registrationForm));
        }
        return "register";
    }
}
