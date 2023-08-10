package pl.coderslab.magazyn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.magazyn.entity.Driver;
import pl.coderslab.magazyn.entity.Employee;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Driver findByUsername(String username);
    Driver findByEmail(String email);
}
