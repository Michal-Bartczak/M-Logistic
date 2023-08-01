package pl.coderslab.magazyn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.magazyn.entity.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
