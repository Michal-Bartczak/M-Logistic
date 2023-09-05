package pl.coderslab.magazyn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.magazyn.generators.TrackingNumberGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    @Min(value = 10, message = "{order.weigh.notnull}")
    private String weigh;
    @NotNull(  message = "{order.price.min}")
    private BigDecimal price;
    private LocalDate creationDate = LocalDate.now();
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.MAGAZYN;

    private String provider = "BRAK";
    @Pattern(regexp = "^\\d{2}-\\d{3}$", message ="{order.zipCodeRecipient.notnull}")
    private String zipCodeRecipient;
   @Size(min =3, message = "{order.cityRecipient.notnull}")
    private String cityRecipient;
    @Size(min =3,message = "{order.streetRecipient.notnull}")
    private String streetRecipient;
    @Size(min =3,message = "{order.nameRecipient.notnull}")
    private String nameRecipient;

    private String trackingNumber = TrackingNumberGenerator.generateTrackingNumber();
    @JsonBackReference
    @ManyToOne
    private Customer customer;

}
