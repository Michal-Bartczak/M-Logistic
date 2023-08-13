package pl.coderslab.magazyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.magazyn.entity.Order;
import pl.coderslab.magazyn.repository.OrderRepository;

import java.util.List;
import java.util.Set;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerService customerService){
        this.orderRepository=orderRepository;

    }
    public List<Order> getAllOrdersSortedByCreationDate() {
        return orderRepository.findAllByOrderByCreationDateDesc();
    }

}
