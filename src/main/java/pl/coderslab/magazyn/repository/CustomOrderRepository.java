package pl.coderslab.magazyn.repository;

import pl.coderslab.magazyn.dto.FilterOrderDTO;
import pl.coderslab.magazyn.entity.Order;
import pl.coderslab.magazyn.entity.OrderStatus;

import java.util.List;

public interface CustomOrderRepository {
    List<Order> orderFilter(FilterOrderDTO filter);

    Long countOrdersByStatusForCurrentMonth(OrderStatus status);

}