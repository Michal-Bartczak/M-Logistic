package pl.coderslab.magazyn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.magazyn.service.AdminService;

@RequestMapping("/admin")
@Controller
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/homepage")
    public String homepageAdmin(Model model){
        model.addAttribute("admin",adminService.getCurrentAdminObject());
        model.addAttribute("orderslist", adminService.getAllOrdersSortedByStatus());
        return "/admin/logInAdmin";
    }
    @GetMapping("customers-list")
    public String getAllCustomers(Model model){
        model.addAttribute("admin",adminService.getCurrentAdminObject());
        model.addAttribute("customerslist", adminService.getAllCustomersSortedById());
        return "/admin/customers-list";
    }
    @GetMapping("employee-list")
    public String getAllEmployeeAndDrivers(Model model){
        model.addAttribute("admin",adminService.getCurrentAdminObject());
        model.addAttribute("employeeslist", adminService.getAllEmployee());
        model.addAttribute("driverslist", adminService.getAllDrivers());
        return "/admin/employee-list";
    }
}
