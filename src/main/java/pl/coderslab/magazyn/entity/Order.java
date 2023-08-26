package pl.coderslab.magazyn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.coderslab.magazyn.generators.TrackingNumberGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
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
    private String provider = "BRAK";
    @NotNull
    private String zipCodeRecipient;
    @NotNull
    private String cityRecipient;
    @NotNull
    private String streetRecipient;
    @NotNull
    private String nameRecipient;

    private String trackingNumber = TrackingNumberGenerator.generateTrackingNumber();
    @JsonIgnore
    @ManyToOne
    private Customer customer;

}
