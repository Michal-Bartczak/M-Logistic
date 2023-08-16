package pl.coderslab.magazyn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.magazyn.entity.Customer;
import pl.coderslab.magazyn.entity.Employee;

import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
    Customer findByEmail(String email);

    List<Customer> findAllByOrderByIdAsc();

}
