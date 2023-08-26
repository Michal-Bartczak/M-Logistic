package pl.coderslab.magazyn.repository;

import pl.coderslab.magazyn.dto.FilterOrderDTO;
import pl.coderslab.magazyn.entity.Order;

import java.util.List;

public interface CustomOrderRepository {
    List<Order> orderFilter(FilterOrderDTO filter);
}