package pl.coderslab.magazyn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.magazyn.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
