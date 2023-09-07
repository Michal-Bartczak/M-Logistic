package pl.coderslab.magazyn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.coderslab.magazyn.dto.FilterOrderDTO;
import pl.coderslab.magazyn.entity.Order;
import pl.coderslab.magazyn.entity.OrderStatus;


import java.util.List;

public interface CustomOrderRepository {
    List<Order> orderFilter(FilterOrderDTO filter);
    Page<Order> orderFilterWithPagination(FilterOrderDTO filter, Pageable pageable);

    Long countOrdersByStatusForCurrentMonth(OrderStatus status);

}