package pl.coderslab.magazyn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.magazyn.dto.OrderAndCustomerDetailsDTO;
import pl.coderslab.magazyn.entity.CustomerDetails;
import pl.coderslab.magazyn.entity.Order;

import pl.coderslab.magazyn.service.CustomerService;
import pl.coderslab.magazyn.service.OrderService;

@RestController
@RequestMapping("/customer")
public class ShippingLabel {
    private final OrderService orderService;
    private final CustomerService customerService;

    @Autowired
    public ShippingLabel(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @GetMapping("/shippingLabel/{trackingNumber}")
    public ResponseEntity<OrderAndCustomerDetailsDTO> getEtykieta(@PathVariable String trackingNumber) {
        Order order = orderService.getOrderByTrackingNumber(trackingNumber);
        CustomerDetails customerDetails = customerService.getCurrentCustomerDetails();

        if(order != null && customerDetails != null) {
            OrderAndCustomerDetailsDTO dto = new OrderAndCustomerDetailsDTO();
            dto.setOrder(order);
            dto.setCustomerDetails(customerDetails);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
