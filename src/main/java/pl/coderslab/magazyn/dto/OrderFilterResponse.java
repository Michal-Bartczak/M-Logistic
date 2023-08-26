package pl.coderslab.magazyn.dto;

import lombok.Data;
import pl.coderslab.magazyn.entity.Driver;
import pl.coderslab.magazyn.entity.Order;

import java.util.List;

@Data
public class OrderFilterResponse {
    private List<Order> orders;
    private String message;
    private List<Driver> drivers;

}
