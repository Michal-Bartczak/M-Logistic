package pl.coderslab.magazyn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.magazyn.entity.DeliveryLog;
import pl.coderslab.magazyn.entity.DeliveryStatus;
import pl.coderslab.magazyn.entity.Driver;


@Repository
public interface DeliveryLogRepository extends JpaRepository<DeliveryLog,Long > {

    @Query("SELECT COUNT(dl) FROM DeliveryLog dl WHERE dl.deliveryStatus = :status AND dl.driver = :driver AND MONTH(dl.deliveryDate) = :currentMonth AND YEAR(dl.deliveryDate) = :currentYear")
    Long countByDeliveryStatusAndDriverForCurrentMonth(@Param("status") DeliveryStatus status, @Param("driver") Driver driver, @Param("currentMonth") int currentMonth, @Param("currentYear") int currentYear);

}


