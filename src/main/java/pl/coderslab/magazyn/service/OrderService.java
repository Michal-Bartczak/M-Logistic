package pl.coderslab.magazyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.magazyn.dto.FilterOrderDTO;
import pl.coderslab.magazyn.entity.*;
import pl.coderslab.magazyn.repository.CustomOrderRepository;
import pl.coderslab.magazyn.repository.DriverRepository;
import pl.coderslab.magazyn.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final DriverRepository driverRepository;
    private final CustomOrderRepository customOrderRepository;
    private final CustomerService customerService;


    @Autowired
    public OrderService(OrderRepository orderRepository, DriverRepository driverRepository, CustomOrderRepository customOrderRepository, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.driverRepository = driverRepository;

        this.customOrderRepository = customOrderRepository;
        this.customerService = customerService;
    }


    public Order getOrderByTrackingNumber(String trackingNumber) {
        return orderRepository.findByTrackingNumber(trackingNumber);
    }

    public List<Order> getAllOrdersSortedByStatusAndProvider() {
        return orderRepository.findAllSortedByStatusAndProvider();
    }

    public Order updateOrderProvider(Long orderId, Long driverId) {
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

    public List<Order> getAllOrdersByCustomerIdSortedByDate() {
        Customer customer = customerService.getCurrentCustomerObject();

        return orderRepository.findAllByCustomerIdOrderByCreationDate(customer.getId());
    }

    public void updateStatusOrderAfterDelivery(Long orderId, String updateStatus) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Order order = optionalOrder.orElseThrow(() -> new RuntimeException("Order not found for ID: " + orderId));

        if (updateStatus.equals("DOSTARCZONO")) {
            order.setStatus(OrderStatus.DOSTARCZONO);
        } else if (updateStatus.equals("NIE DOSTARCZONO")) {
            order.setStatus(OrderStatus.MAGAZYN);
            order.setProvider("BRAK");
        } else {
            throw new IllegalArgumentException("Unknown updateStatus value: " + updateStatus);
        }

        orderRepository.save(order);
    }

    public List<Order> orderFilter(FilterOrderDTO filter) {
        List<Order> filterList;

        // Filter by trackingNumber
        if (filter.getFilterText() == null || filter.getFilterText().matches(".*[a-zA-Z]+.*")) {
            filterList = orderRepository.findAll();
        } else {
            filterList = orderRepository.findByTrackingNumberContaining(filter.getFilterText());
        }

        // Filter by date
        if (filter.getFilterData() != null) {
            filterList = filterList.stream()
                    .filter(order -> order.getCreationDate().equals(filter.getFilterData()))
                    .collect(Collectors.toList());
        }

        // Filter by status
        if (!"WSZYSTKIE".equals(filter.getStatus())) {
            OrderStatus status = OrderStatus.valueOf(filter.getStatus());
            filterList = filterList.stream()
                    .filter(order -> order.getStatus().equals(status))
                    .collect(Collectors.toList());
        }

        // Filter by kind
        if (filter.getKindEur() == ShipmentDimensions.EUR && filter.getKindHp() != ShipmentDimensions.HP) {
            filterList = filterList.stream()
                    .filter(order -> order.getDimensions() == ShipmentDimensions.EUR)
                    .collect(Collectors.toList());
        } else if (filter.getKindEur() != ShipmentDimensions.EUR && filter.getKindHp() == ShipmentDimensions.HP) {
            filterList = filterList.stream()
                    .filter(order -> order.getDimensions() == ShipmentDimensions.HP)
                    .collect(Collectors.toList());
        }

        return filterList;
    }
    public List<Order> filterOrders(FilterOrderDTO filter) {
        return customOrderRepository.orderFilter(filter);
    }

}
