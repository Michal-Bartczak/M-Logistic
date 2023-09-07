package pl.coderslab.magazyn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.magazyn.entity.CustomerDetails;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails,Long> {


}
