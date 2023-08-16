package pl.coderslab.magazyn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import pl.coderslab.magazyn.entity.CustomerDetails;
import pl.coderslab.magazyn.entity.Order;
@Component
@Setter
@Getter

public class OrderAndCustomerDetailsDTO {
    private Order order;
    private CustomerDetails customerDetails;

}
