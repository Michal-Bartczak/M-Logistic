package pl.coderslab.magazyn.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.magazyn.dto.FilterOrderDTO;
import pl.coderslab.magazyn.dto.OrderFilterResponse;
import pl.coderslab.magazyn.dto.OrderReportDTO;
import pl.coderslab.magazyn.entity.Order;
import pl.coderslab.magazyn.service.DriverService;
import pl.coderslab.magazyn.service.OrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeRestController {
    private final OrderService orderService;
    private final DriverService driverService;

    public EmployeeRestController(OrderService orderService, DriverService driverService) {
        this.orderService = orderService;
        this.driverService = driverService;
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

    @PostMapping("employee/filter")
    public ResponseEntity<?> filterOrders(@RequestBody FilterOrderDTO filter){
        List<Order> filteredOrders = orderService.filterOrders(filter);
        OrderFilterResponse response = new OrderFilterResponse();

        if (filteredOrders.isEmpty()) {
            response.setMessage("Żadna z przesyłek nie spełnia kryteriów");
        }
            response.setOrders(filteredOrders);
            response.setDrivers(driverService.getAllDrivers());

        return ResponseEntity.ok(response);
    }
    @GetMapping("employee/orderStatsForCurrentMonth")
    public OrderReportDTO getOrderStatsForCurrentMonth() {
       return orderService.getOrderReportForCurrentMonth();
    }
}
