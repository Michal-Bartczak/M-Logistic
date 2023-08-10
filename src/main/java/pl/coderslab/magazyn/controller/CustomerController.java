package pl.coderslab.magazyn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {
    @GetMapping("/homepage/customer")
    public String homePageCustomer(){
        return "logInCustomer";
    }
}
