package pl.coderslab.magazyn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.magazyn.entity.Customer;
import pl.coderslab.magazyn.entity.CustomerDetails;
import pl.coderslab.magazyn.entity.Order;
import pl.coderslab.magazyn.entity.ShipmentDimensions;
import pl.coderslab.magazyn.service.CustomerDetailsService;
import pl.coderslab.magazyn.service.CustomerService;
import pl.coderslab.magazyn.service.OrderService;
import pl.coderslab.magazyn.service.UserService;

import javax.validation.Valid;

@RequestMapping("/customer")
@Controller
public class CustomerController {
    private final CustomerService customerService;

    private final CustomerDetailsService customerDetailsService;
    private final UserService userService;
    private final OrderService orderService;

    public CustomerController(CustomerService customerService, CustomerDetailsService customerDetailsService, UserService userService, OrderService orderService) {
        this.customerService = customerService;

        this.customerDetailsService = customerDetailsService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/homepage")
    public String homePageCustomer(Model model) {
        model.addAttribute("customer", customerService.getCurrentCustomerObject());
        model.addAttribute("orderList", orderService.getAllOrdersByCustomerIdSortedByDate());
        return "customer/homepageCustomer";
    }

    @GetMapping("/editDetails")
    public String editCustomerDetails(Model model) {
        model.addAttribute("customer", customerService.getCurrentCustomerObject());
        model.addAttribute("editForm", customerService.getCurrentCustomerDetails());
        return "/customer/editDetails";
    }

    @PostMapping("/editDetails")
    public String saveCustomerDetails(@Valid @ModelAttribute("editForm") CustomerDetails customerDetails,
                                      BindingResult bindingResult,
                                      Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("editForm", customerDetails);
            return "customer/editDetails";
        }

        Customer customer = customerService.getCurrentCustomerObject();
        customerDetailsService.addOrUpdateWhenExistCustomerDetails(customer, customerDetails);

        return "redirect:/customer/editDetails?update=true";
    }

    @GetMapping("/edit-password")
    public String editPassword(Model model) {
        model.addAttribute("customer", customerService.getCurrentCustomerObject());
        return "/customer/editPassword";
    }

    @PostMapping("/edit-password")
    public String saveNewPassword(@RequestParam String oldPassword,
                                  @RequestParam String newPassword
    ) {

        if (customerService.saveNewPasswordCustomer(oldPassword, newPassword)) {
            return "redirect:/customer/edit-password?save=true";
        }
        return "redirect:/customer/edit-password?save=false";

    }

    @GetMapping("/send")
    public String sendPackage(Model model) {
        model.addAttribute("customer", customerService.getCurrentCustomerObject());
        model.addAttribute("dimensions", ShipmentDimensions.values());
        model.addAttribute("detailsPackage", new Order());
        return "/customer/sendPackage";
    }

    @PostMapping("/send")
    public String saveSendPackage(@Valid @ModelAttribute("detailsPackage") Order order,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("detailsPackage", order);
            model.addAttribute("hasDetails", customerService.hasCurrentCustomerDetails());
            model.addAttribute("dimensions", ShipmentDimensions.values());
            model.addAttribute("customer", customerService.getCurrentCustomerObject());
            return "/customer/sendPackage";
        }
        customerService.addSendPackageCustomer(order);
        return "redirect:/customer/send?sent=true";
    }


}
