package pl.coderslab.magazyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.magazyn.BarCodeGenerator;
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

        ShippingLabelDTO label = new ShippingLabelDTO();
        label.setTrackingNumber(trackingNumber);

        label.setCityRecipient(order.getCityRecipient());
        label.setWeigh(order.getWeigh());
        label.setNameRecipient(order.getNameRecipient());
        try {
            byte[] barcodeImage = barCodeGenerator.generateBarcode(trackingNumber);
            label.setBarcode(encodeImageToBase64(barcodeImage));
        } catch (Exception e) {
            // Tutaj możesz zalogować błąd lub obsłużyć go inaczej, jeśli generowanie kodu kreskowego się nie powiedzie
            e.printStackTrace();
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
    public ShippingLabelDTO getShippingLabelByTrackingNumberForEmployee(String trackingNumber){
        Order order = orderService.getOrderByTrackingNumber(trackingNumber);
       Customer customer = order.getCustomer();
      CustomerDetails customerDetails = customer.getCustomerDetails();
        ShippingLabelDTO label = new ShippingLabelDTO();
        label.setTrackingNumber(trackingNumber);

        label.setCityRecipient(order.getCityRecipient());
        label.setWeigh(order.getWeigh());
        label.setNameRecipient(order.getNameRecipient());
        try {
            byte[] barcodeImage = barCodeGenerator.generateBarcode(trackingNumber);
            label.setBarcode(encodeImageToBase64(barcodeImage));
        } catch (Exception e) {
            // Tutaj możesz zalogować błąd lub obsłużyć go inaczej, jeśli generowanie kodu kreskowego się nie powiedzie
            e.printStackTrace();
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
    private String encodeImageToBase64(byte[] imageByteArray) {
        return Base64.getEncoder().encodeToString(imageByteArray);
    }

}
