package pl.coderslab.magazyn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ShipmentDimensions dimensions;
    @NotNull
    private String weigh;
    @Min(100)
    private BigDecimal price;
    private LocalDate creationDate =LocalDate.now();
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.MAGAZYN;
    @NotNull
    private String zipCodeRecipient;
    @NotNull
    private String cityRecipient;
    @NotNull
    private String streetRecipient;
    @NotNull
    private String nameRecipient;

    private String trackingNumber = TrackingNumberGenerator.generateTrackingNumber();
    @ManyToOne
    private Customer customer;

}
