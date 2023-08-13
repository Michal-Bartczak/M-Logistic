package pl.coderslab.magazyn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.magazyn.service.EmployeeService;
import pl.coderslab.magazyn.service.OrderService;

@RequestMapping("/employee")
@Controller
public class EmployeeController {
    private final OrderService orderService;
   private final EmployeeService employeeService;


    @Autowired
    public EmployeeController(OrderService orderService, EmployeeService employeeService) {
        this.orderService = orderService;
        this.employeeService = employeeService;





    }

    @GetMapping("/homepage")
    public String homePageDriver(Model model) {
        model.addAttribute("orderList", orderService.getAllOrdersSortedByCreationDate());
        model.addAttribute("employee", employeeService.getCurrentEmployeeObject());
        return "/employees/homepageEmployee";
    }




}
