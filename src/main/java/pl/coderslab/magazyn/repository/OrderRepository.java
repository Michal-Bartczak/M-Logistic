package pl.coderslab.magazyn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.magazyn.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
