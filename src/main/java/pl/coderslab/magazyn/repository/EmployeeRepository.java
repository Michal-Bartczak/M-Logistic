package pl.coderslab.magazyn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.magazyn.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
