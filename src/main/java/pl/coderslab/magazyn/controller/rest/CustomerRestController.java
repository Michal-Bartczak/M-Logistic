package pl.coderslab.magazyn.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.magazyn.service.CustomerService;

import java.security.Principal;

@RestController
public class CustomerRestController {

    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/customer/hasCustomerDetails")
    public ResponseEntity<Boolean> checkCustomerDetails() {
        boolean hasDetails = customerService.hasCurrentCustomerDetails();
        return ResponseEntity.ok(hasDetails);
    }
}
