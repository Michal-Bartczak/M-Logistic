package pl.coderslab.magazyn.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "delivery_logs")
@Data
public class DeliveryLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    public DeliveryStatus deliveryStatus;
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    private LocalDate deliveryDate = LocalDate.now();
}
