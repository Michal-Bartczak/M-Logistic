package pl.coderslab.magazyn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.magazyn.entity.Order;
import pl.coderslab.magazyn.entity.OrderStatus;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByOrderByCreationDateDesc();
     List<Order> findByProviderAndStatus(String provider, OrderStatus status);
    @Query("SELECT o FROM Order o ORDER BY CASE WHEN o.status = 'MAGAZYN' AND o.provider = 'BRAK' THEN 1 WHEN o.status = 'MAGAZYN' THEN 2 WHEN o.status = 'DOSTAWA' THEN 3 WHEN o.status = 'DOSTARCZONO' THEN 4 ELSE 5 END")
    List<Order> findAllSortedByStatusAndProvider();
    
    Order findByTrackingNumber(String trackingNumber);

    @Query("SELECT o FROM Order o WHERE o.customer.id = :customerId ORDER BY o.creationDate DESC")
    List<Order> findAllByCustomerIdOrderByCreationDate(@Param("customerId") Long customerId);



}
