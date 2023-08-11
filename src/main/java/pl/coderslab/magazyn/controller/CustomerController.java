package pl.coderslab.magazyn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.coderslab.magazyn.generic.Converter;
import pl.coderslab.magazyn.repository.CustomerRepository;
import pl.coderslab.magazyn.service.CustomerService;

@Controller
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    public CustomerController(Converter converter, CustomerService customerService, CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;

    }

    @GetMapping("/homepage/customer")
    public String homePageCustomer(Model model){
        model.addAttribute("customer",customerRepository.findByUsername(customerService.getCurrentUsername()));
        return "/customer/logInCustomer";
    }
}
