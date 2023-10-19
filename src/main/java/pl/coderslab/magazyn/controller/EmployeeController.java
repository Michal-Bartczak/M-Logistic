package pl.coderslab.magazyn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.magazyn.service.CustomerService;
import pl.coderslab.magazyn.service.DriverService;
import pl.coderslab.magazyn.service.EmployeeService;
import pl.coderslab.magazyn.service.OrderService;

@RequestMapping("/employee")
@Controller
public class EmployeeController {
    private final OrderService orderService;
    private final EmployeeService employeeService;
    private final CustomerService customerService;
    private final DriverService driverService;


    public EmployeeController(OrderService orderService, EmployeeService employeeService, CustomerService customerService, DriverService driverService) {
        this.orderService = orderService;
        this.employeeService = employeeService;
        this.customerService = customerService;
        this.driverService = driverService;
    }

    @GetMapping("/homepage")
    public String getHomepageEmployee(Model model) {
//        model.addAttribute("orderList", orderService.getAllOrdersSortedByStatusAndProvider());
        model.addAttribute("employee", employeeService.getCurrentEmployeeObject());
        model.addAttribute("driversList", driverService.getAllDrivers());
        return "employee/homepageEmployee";
    }

    @GetMapping("/edit-password")
    public String editPassword(Model model) {
        model.addAttribute("employee", employeeService.getCurrentEmployeeObject());
        return "employee/editPassword";
    }

    @PostMapping("/edit-password")
    public String savePassword(@RequestParam String oldPassword,
                               @RequestParam String newPassword) {
        if (employeeService.saveNewPasswordEmployee(oldPassword, newPassword)) {
            return "redirect:/employee/edit-password?save=true";
        }
        return "redirect:/employee/edit-password?save=false";
    }

    @GetMapping("/users-list")
    public String showAllCustomers(Model model) {
        model.addAttribute("employee", employeeService.getCurrentEmployeeObject());
        model.addAttribute("customers", customerService.getAllCustomers());
        return "employee/customersList";
    }

    @GetMapping("/employees-list")
    public String showAllEmployees(Model model) {
        model.addAttribute("employeeOwn", employeeService.getCurrentEmployeeObject());
        model.addAttribute("employees", employeeService.findAllEmployees());
        model.addAttribute("drivers", employeeService.findAllDrivers());
        return "employee/employeesAndDrivers";
    }

    @GetMapping("/raport")
    public String showRaport(Model model) {
        model.addAttribute("employee", employeeService.getCurrentEmployeeObject());
        return "employee/orderRaport";
    }
}
