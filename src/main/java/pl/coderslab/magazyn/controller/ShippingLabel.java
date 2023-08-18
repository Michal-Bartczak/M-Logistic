package pl.coderslab.magazyn.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.magazyn.dto.ShippingLabelDTO;

import pl.coderslab.magazyn.service.DtoService;

@RestController
@RequestMapping("/customer")
public class ShippingLabel {
      private final   DtoService service ;

    public ShippingLabel(DtoService service) {
        this.service = service;
    }

    @GetMapping("/shippingLabel/{trackingNumber}")
    public ResponseEntity<ShippingLabelDTO> getEtykieta(@PathVariable String trackingNumber) {
        ShippingLabelDTO shippingLabel = service.getShippingLabelByTrackingNumber(trackingNumber);

        if (shippingLabel != null) {
            return ResponseEntity.ok(shippingLabel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
