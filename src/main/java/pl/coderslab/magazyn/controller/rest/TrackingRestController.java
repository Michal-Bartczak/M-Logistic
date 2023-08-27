package pl.coderslab.magazyn.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.magazyn.dto.OrderStatusDTO;
import pl.coderslab.magazyn.service.OrderService;

@RestController
public class TrackingRestController {
    private final OrderService orderService;

    public TrackingRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/track-package/status")
    public ResponseEntity<?> getStatusByTrackingNumber(@RequestBody String trackingNumber) {

        OrderStatusDTO orderStatus = orderService.getOrderStatusByTrackingNumber(trackingNumber);
        if (orderStatus == null) {
            return new ResponseEntity<>("Nie znaleziono paczki o numerze: " + trackingNumber, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderStatus, HttpStatus.OK);
    }


}
