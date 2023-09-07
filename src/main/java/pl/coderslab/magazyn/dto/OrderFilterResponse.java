package pl.coderslab.magazyn.dto;

import lombok.*;
import pl.coderslab.magazyn.entity.Driver;
import pl.coderslab.magazyn.entity.Order;

import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderFilterResponse {
    private List<Order> orders;
    private String message;
    private List<Driver> drivers;
    private int totalPages;

}
