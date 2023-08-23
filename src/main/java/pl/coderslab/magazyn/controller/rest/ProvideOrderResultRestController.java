package pl.coderslab.magazyn.controller.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.magazyn.service.OrderService;

@RestController
public class ProvideOrderResultRestController {
    private final OrderService orderService;

    public ProvideOrderResultRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("driver/editStatus/{orderId}")
    public String resultProvideOrder(@PathVariable Long orderId,
                                     @RequestBody String status){
        orderService.updateStatusOrderAfterDelivery(orderId,status);
        return "odpowiedź z serwera";
    }
}
