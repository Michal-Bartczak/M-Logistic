package pl.coderslab.magazyn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @NotNull(message = "{order.weigh.notnull}")
    private String weigh;
    @Min(value = 100, message = "{order.price.min}")
    private BigDecimal price;
    private LocalDate creationDate =LocalDate.now();
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.MAGAZYN;

    private String provider = "BRAK";
    @NotNull(message = "{order.zipCodeRecipient.notnull}")
    private String zipCodeRecipient;
    @NotNull(message = "{order.cityRecipient.notnull}")
    private String cityRecipient;
    @NotNull(message = "{order.streetRecipient.notnull}")
    private String streetRecipient;
    @NotNull(message = "{order.nameRecipient.notnull}")
    private String nameRecipient;

    private String trackingNumber = TrackingNumberGenerator.generateTrackingNumber();
    @JsonBackReference
    @ManyToOne
    private Customer customer;

}
