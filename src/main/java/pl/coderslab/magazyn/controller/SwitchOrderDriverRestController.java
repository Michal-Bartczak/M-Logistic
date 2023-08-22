package pl.coderslab.magazyn.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.magazyn.entity.Order;
import pl.coderslab.magazyn.service.OrderService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SwitchOrderDriverRestController {
    private final OrderService orderService;

    public SwitchOrderDriverRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/employee/updateOrderDriver/{orderId}/{driverId}")
    public ResponseEntity<Map<String, Object>> updateOrderDriver(@PathVariable Long orderId,
                                                                 @PathVariable Long driverId) {
        Map<String, Object> response = new HashMap<>();
        try {
            Order updatedOrder = orderService.updateOrderProvider(orderId, driverId);
            response.put("status", "success");
            response.put("message", "Order updated successfully");
            response.put("data", updatedOrder);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
