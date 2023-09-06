package pl.coderslab.magazyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.magazyn.exception.BarcodeGenerationException;
import pl.coderslab.magazyn.generators.BarCodeGenerator;
import pl.coderslab.magazyn.dto.ShippingLabelDTO;
import pl.coderslab.magazyn.entity.Customer;
import pl.coderslab.magazyn.entity.CustomerDetails;
import pl.coderslab.magazyn.entity.Order;

import java.util.Base64;

@Service
public class DtoService {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final BarCodeGenerator barCodeGenerator;

    @Autowired
    public DtoService(OrderService orderService, CustomerService customerService, BarCodeGenerator barCodeGenerator) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.barCodeGenerator = barCodeGenerator;
    }

    public ShippingLabelDTO getShippingLabelByTrackingNumberForCustomer(String trackingNumber) {
        Order order = orderService.getOrderByTrackingNumber(trackingNumber);
        CustomerDetails customerDetails = customerService.getCurrentCustomerDetails();
        return createShippingLabel(order, customerDetails);
    }

    public ShippingLabelDTO getShippingLabelByTrackingNumberForEmployee(String trackingNumber) {
        Order order = orderService.getOrderByTrackingNumber(trackingNumber);
        Customer customer = order.getCustomer();
        CustomerDetails customerDetails = customer.getCustomerDetails();
        return createShippingLabel(order, customerDetails);
    }

    private String encodeImageToBase64(byte[] imageByteArray) {
        return Base64.getEncoder().encodeToString(imageByteArray);
    }
    private ShippingLabelDTO createShippingLabel(Order order, CustomerDetails customerDetails) {
        ShippingLabelDTO label = new ShippingLabelDTO();
        label.setTrackingNumber(order.getTrackingNumber());
        label.setCityRecipient(order.getCityRecipient());
        label.setWeigh(order.getWeigh());
        label.setNameRecipient(order.getNameRecipient());

        try {
            byte[] barcodeImage = barCodeGenerator.generateBarcode(order.getTrackingNumber());
            label.setBarcode(encodeImageToBase64(barcodeImage));
        } catch (Exception e) {
            throw new BarcodeGenerationException("Nie udało się utworzyć etykiety wysyłkowej", e);
        }

        label.setZipCodeRecipient(order.getZipCodeRecipient());
        label.setOrderCreated(order.getCreationDate());
        label.setStreetRecipient(order.getStreetRecipient());
        label.setDimensions(order.getDimensions().toString());
        label.setNameCompanySender(customerDetails.getNameCompanySender());
        label.setZipCodeSender(customerDetails.getZipCodeSender());
        label.setCitySender(customerDetails.getCitySender());
        label.setStreetSender(customerDetails.getStreetSender());

        return label;
    }


}
