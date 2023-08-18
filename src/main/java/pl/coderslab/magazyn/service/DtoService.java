package pl.coderslab.magazyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.magazyn.dto.ShippingLabelDTO;
import pl.coderslab.magazyn.entity.CustomerDetails;
import pl.coderslab.magazyn.entity.Order;

@Service
public class DtoService {

    private final OrderService orderService;
    private final CustomerService customerService;

    @Autowired
    public DtoService(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    public ShippingLabelDTO getShippingLabelByTrackingNumber(String trackingNumber) {
        Order order = orderService.getOrderByTrackingNumber(trackingNumber);
        CustomerDetails customerDetails = customerService.getCurrentCustomerDetails();

        ShippingLabelDTO label = new ShippingLabelDTO();
        label.setTrackingNumber(trackingNumber);

        label.setCityRecipient(order.getCityRecipient());
        label.setWeigh(order.getWeigh());
        label.setNameRecipient(order.getNameRecipient());
        label.setZipCodeRecipient(order.getZipCodeRecipient());
        label.setOrderCreated(order.getCreationDate());

        label.setNameCompanySender(customerDetails.getNameCompanySender());
        label.setZipCodeSender(customerDetails.getZipCodeSender());
        label.setCitySender(customerDetails.getCitySender());
        label.setStreetSender(customerDetails.getStreetSender());

        return label;
    }

}
