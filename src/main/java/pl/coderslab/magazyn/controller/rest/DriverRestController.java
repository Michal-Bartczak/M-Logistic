package pl.coderslab.magazyn.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.magazyn.dto.DeliveryOrderDriverRaport;
import pl.coderslab.magazyn.service.DriverService;

@RestController
public class DriverRestController {

    private final DriverService driverService;

    public DriverRestController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/driver/effectivenessRaport")
    public DeliveryOrderDriverRaport getDeliveryDriverRaport(){
        return driverService.getDeliveryDriverRaport();
    }

}
