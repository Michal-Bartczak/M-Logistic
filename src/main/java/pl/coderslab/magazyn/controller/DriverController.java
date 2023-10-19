package pl.coderslab.magazyn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.magazyn.service.DriverService;

@RequestMapping("/driver")
@Controller
public class DriverController {
    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }


    @GetMapping("/homepage")
    public String showHomepage(Model model){
        model.addAttribute("driver", driverService.getCurrentDriverObject());
        model.addAttribute("orderlist", driverService.getAllOrdersForCurrentDriver());
        return "driver/homepageDriver";
    }
    @GetMapping("/edit-password")
    public String editPasswordDriver(Model model){
        model.addAttribute("driver", driverService.getCurrentDriverObject());
        return "driver/editPassword";
    }
    @PostMapping("/edit-password")
    public String saveNewPasswordDriver(@RequestParam String oldPassword,
                                        @RequestParam String newPassword) {
        if (driverService.saveNewPasswordDriver(oldPassword, newPassword)) {
            return "redirect:/driver/edit-password?update=true";
        }
        return "redirect:/driver/edit-password?update=false";
    }

    @GetMapping("/raport")
    public String getRaport(Model model){
        model.addAttribute("driver", driverService.getCurrentDriverObject());
        return "driver/raportDriver";
    }

}
