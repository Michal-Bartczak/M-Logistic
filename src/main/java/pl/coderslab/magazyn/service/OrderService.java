package pl.coderslab.magazyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.coderslab.magazyn.dto.FilterOrderDTO;
import pl.coderslab.magazyn.dto.OrderReportDTO;
import pl.coderslab.magazyn.dto.OrderStatusDTO;
import pl.coderslab.magazyn.entity.*;
import pl.coderslab.magazyn.exception.OrderNotFoundException;
import pl.coderslab.magazyn.repository.CustomOrderRepository;
import pl.coderslab.magazyn.repository.CustomerRepository;
import pl.coderslab.magazyn.repository.DriverRepository;
import pl.coderslab.magazyn.repository.OrderRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final DriverRepository driverRepository;
    private final CustomOrderRepository customOrderRepository;
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final DriverService driverService;

    private final String DELIVERED = "DOSTARCZONO";
    private final String NOT_DELIVERED = "NIE DOSTARCZONO";
    private final String LACK = "BRAK";
    private final String ALL = "WSZYSTKIE";



    public OrderService(OrderRepository orderRepository, DriverRepository driverRepository, CustomOrderRepository customOrderRepository,
                        CustomerService customerService, CustomerRepository customerRepository, DriverService driverService) {
        this.orderRepository = orderRepository;
        this.driverRepository = driverRepository;
        this.customOrderRepository = customOrderRepository;
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.driverService = driverService;
    }


    public Order getOrderByTrackingNumber(String trackingNumber) {
        return orderRepository.findByTrackingNumber(trackingNumber);
    }

    public List<Order> getAllOrdersSortedByStatusAndProvider() {
        return orderRepository.findAllSortedByStatusAndProvider();
    }


    public Order updateOrderProvider(Long orderId, Long driverId)  {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        Optional<Driver> driverOptional = driverRepository.findById(driverId);
        if (orderOptional.isEmpty() || driverOptional.isEmpty()) {
            throw new OrderNotFoundException("Zamówienie lub kierowca nie istnieją");
        }
        Order order = orderOptional.get();
        Driver driver = driverOptional.get();
        order.setStatus(OrderStatus.DOSTAWA);
        order.setProvider(driver.getUsername());
        orderRepository.save(order);
        return order;
    }

    public List<Order> getAllOrdersByCustomerIdSortedByDate() {
        Customer customer = customerService.getCurrentCustomerObject();

        return orderRepository.findAllByCustomerIdOrderByCreationDate(customer.getId());
    }

    public void updateStatusOrderAfterDelivery(Long orderId, String updateStatus) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Order order = optionalOrder.orElseThrow(() -> new RuntimeException("Order not found for ID: " + orderId));
        DeliveryLog deliveryLog = new DeliveryLog();
        Driver driver = driverService.getCurrentDriverObject();
        if (updateStatus.equals(DELIVERED)) {
            order.setStatus(OrderStatus.DOSTARCZONO);
            deliveryLog.setDeliveryStatus(DeliveryStatus.DOSTARCZONO);
            deliveryLog.setDriver(driver);
            driver.addLog(deliveryLog);
        } else if (updateStatus.equals(NOT_DELIVERED)) {
            order.setStatus(OrderStatus.MAGAZYN);
            order.setProvider(LACK);
            deliveryLog.setDeliveryStatus(DeliveryStatus.NIE_DOSTARCZONO);
            deliveryLog.setDriver(driver);
            driver.addLog(deliveryLog);
        } else {
            throw new IllegalArgumentException("Unknown updateStatus value: " + updateStatus);
        }

        orderRepository.save(order);
    }

    // filtrowanie na całej liście
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
        if (!ALL.equals(filter.getStatus())) {
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

    public OrderReportDTO getOrderReportForCurrentMonth() {
        Long totalOrders = customOrderRepository.countOrdersByStatusForCurrentMonth(null);
        Long warehouseOrders = customOrderRepository.countOrdersByStatusForCurrentMonth(OrderStatus.MAGAZYN);
        Long inDeliveryOrders = customOrderRepository.countOrdersByStatusForCurrentMonth(OrderStatus.DOSTAWA);
        Long deliveredOrders = customOrderRepository.countOrdersByStatusForCurrentMonth(OrderStatus.DOSTARCZONO);
        Long countUsers = customerRepository.count();
        Long countDrivers = driverRepository.count();


        return new OrderReportDTO(totalOrders, warehouseOrders, inDeliveryOrders, deliveredOrders, countUsers, countDrivers);
    }

    public OrderStatusDTO getOrderStatusByTrackingNumber(String trackingNumber) {
        Order order = getOrderByTrackingNumber(trackingNumber);

        if (order == null) {
            return new OrderStatusDTO(null, "Nie można znaleźć paczki o numerze: " + trackingNumber);
        }

        return new OrderStatusDTO(order.getStatus(), null);
    }

    public Page<Order> filterOrdersWithPagination(FilterOrderDTO filterOrderDTO, Pageable pageable){
        return customOrderRepository.orderFilterWithPagination(filterOrderDTO,pageable);
    }

}
