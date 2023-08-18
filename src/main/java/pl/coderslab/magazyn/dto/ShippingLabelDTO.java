package pl.coderslab.magazyn.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Setter
@Getter

public class ShippingLabelDTO {

    private String weigh;
    private String zipCodeRecipient;
    private String cityRecipient;
    private String streetRecipient;

    private String nameRecipient;

    private String trackingNumber;

    private String nameCompanySender;

    private String zipCodeSender;

    private String citySender;

    private String streetSender;

    private LocalDate orderCreated;

}
