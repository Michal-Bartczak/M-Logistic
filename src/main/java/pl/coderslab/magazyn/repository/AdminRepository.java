package pl.coderslab.magazyn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.magazyn.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {


}
