package pl.coderslab.magazyn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor

public class DeliveryOrderDriverRaport {
    private Long delivered;
    private Long notDelivered;
    private double effectivenessInPercent;
}
