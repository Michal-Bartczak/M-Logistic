package pl.coderslab.magazyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.magazyn.entity.Customer;
import pl.coderslab.magazyn.entity.Driver;
import pl.coderslab.magazyn.entity.Order;
import pl.coderslab.magazyn.entity.OrderStatus;
import pl.coderslab.magazyn.repository.DriverRepository;
import pl.coderslab.magazyn.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final DriverRepository driverRepository;
    private final CustomerService customerService;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerService customerService, DriverRepository driverRepository, CustomerService customerService1){
        this.orderRepository=orderRepository;

        this.driverRepository = driverRepository;
        this.customerService = customerService1;
    }
    public List<Order> getAllOrdersSortedByCreationDate() {
        return orderRepository.findAllByOrderByCreationDateDesc();
    }
    public Order getOrderByTrackingNumber(String trackingNumber){
        return orderRepository.findByTrackingNumber(trackingNumber);
    }
    public List<Order> getAllOrdersSortedByStatusAndProvider(){
        return orderRepository.findAllSortedByStatusAndProvider();
    }
    public Order updateOrderProvider(Long orderId, Long driverId ){
       Optional<Order> orderOptional = orderRepository.findById(orderId);
        Optional<Driver> driverOptional = driverRepository.findById(driverId);
        if (orderOptional.isEmpty() || driverOptional.isEmpty()) {
            throw new RuntimeException("Order or Driver not found");
        }
        Order order = orderOptional.get();
        Driver driver = driverOptional.get();
        order.setStatus(OrderStatus.DOSTAWA);
        order.setProvider(driver.getUsername());
        return orderRepository.save(order);
    }
    public List<Order> getAllOrdersByCustomerIdSortedByDate(){
        Customer customer = customerService.getCurrentCustomerObject();

        return orderRepository.findAllByCustomerIdOrderByCreationDate(customer.getId());
    }
}
