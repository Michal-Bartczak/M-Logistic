package pl.coderslab.magazyn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.magazyn.service.CustomerService;
import pl.coderslab.magazyn.service.EmployeeService;
import pl.coderslab.magazyn.service.OrderService;

@RequestMapping("/employee")
@Controller
public class EmployeeController {
    private final OrderService orderService;
   private final EmployeeService employeeService;
   private final CustomerService customerService;


    @Autowired
    public EmployeeController(OrderService orderService, EmployeeService employeeService, CustomerService customerService) {
        this.orderService = orderService;
        this.employeeService = employeeService;


        this.customerService = customerService;
    }

    @GetMapping("/homepage")
    public String homePageDriver(Model model) {
        model.addAttribute("orderList", orderService.getAllOrdersSortedByCreationDate());
        model.addAttribute("employee", employeeService.getCurrentEmployeeObject());
        return "/employees/homepageEmployee";
    }
    @GetMapping ("edit-password")
    public String editPassword(Model model){
        model.addAttribute("employee", employeeService.getCurrentEmployeeObject());
        return "/employees/editPassword";
    }
    @PostMapping("edit-password")
    public String savePassword(@RequestParam String oldPassword,
                               @RequestParam String newPassword){
        if (employeeService.saveNewPasswordEmployee(oldPassword, newPassword)){
            return "redirect:/employee/edit-password?save=true";
        }
        return "redirect:/employee/edit-password?save=false";
    }
    @GetMapping("users-list")
    public String showAllCustomers(Model model){
        model.addAttribute("employee", employeeService.getCurrentEmployeeObject());
        model.addAttribute("customers", customerService.getAllCustomers());
        return "/employees/customersList";
    }
    @GetMapping("employees-list")
    public String showAllEmployee(Model model){
        model.addAttribute("employeeOwn", employeeService.getCurrentEmployeeObject());
        model.addAttribute("employees", employeeService.findAllEmployees());
        model.addAttribute("drivers", employeeService.findAllDrivers());
        return "/employees/employeesAndDrivers";
    }



}
