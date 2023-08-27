package pl.coderslab.magazyn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.coderslab.magazyn.entity.OrderStatus;
@Data
@AllArgsConstructor
public class OrderStatusDTO {
    private OrderStatus orderStatus;
    private String errorMessage;
}
