package pl.coderslab.magazyn.controller;

import com.google.zxing.qrcode.decoder.Mode;
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

import javax.servlet.http.HttpSession;

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

    @PostMapping("/driver")
    public String registerDriver(@ModelAttribute RegistrationForm registrationForm,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            return "/register";
        }
        driverRepository.save(driverService.compereToDriver(registrationForm));
        model.addAttribute("registered", true);
        return "redirect:/login";
    }
    @PostMapping("/customer")
    public String registerCustomer(@ModelAttribute RegistrationForm registrationForm,
                                   BindingResult bindingResult,
                                   Model model){
        if (bindingResult.hasErrors()){
            return "register";
        }
        customerRepository.save(customerService.compereToCustomer(registrationForm));
        model.addAttribute("registered", "Udało Ci się założyć konto, teraz możesz się zalogować!");
        return "redirect:/login";
    }
}
