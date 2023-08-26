package pl.coderslab.magazyn.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
import pl.coderslab.magazyn.entity.ShipmentDimensions;

import java.time.LocalDate;

@Data
@Component
public class FilterOrderDTO {
    private String filterText;
    private LocalDate filterData;
    private String status;
    private ShipmentDimensions kindEur;
    private ShipmentDimensions kindHp;


}
