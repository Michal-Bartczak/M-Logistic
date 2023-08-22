package pl.coderslab.magazyn.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.magazyn.dto.ShippingLabelDTO;
import pl.coderslab.magazyn.service.DtoService;

@RestController
public class ShippingLabelRestController {
      private final   DtoService service ;

    public ShippingLabelRestController(DtoService service) {
        this.service = service;
    }

    @GetMapping("/customer/shippingLabel/{trackingNumber}")
    public ResponseEntity<ShippingLabelDTO> getShippingLabelForCustomer(@PathVariable String trackingNumber) {
        ShippingLabelDTO shippingLabel = service.getShippingLabelByTrackingNumberForCustomer(trackingNumber);

        if (shippingLabel != null) {
            return ResponseEntity.ok(shippingLabel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/employee/shippingLabel/{trackingNumber}")
    public ResponseEntity<ShippingLabelDTO> getShippingLabelForEmployee(@PathVariable String trackingNumber) {
        ShippingLabelDTO shippingLabel = service.getShippingLabelByTrackingNumberForEmployee(trackingNumber);

        if (shippingLabel != null) {
            return ResponseEntity.ok(shippingLabel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
