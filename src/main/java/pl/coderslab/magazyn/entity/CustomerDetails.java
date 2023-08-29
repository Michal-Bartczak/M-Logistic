package pl.coderslab.magazyn.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Getter
@Setter
public class CustomerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 5, max = 30, message = "{customerdetails.nameCompanySender.size}")
    private String nameCompanySender;
    @Pattern(regexp = "\\d{2}-\\d{3}", message = "{customerdetails.zipCodeSender.pattern}")
    private String zipCodeSender;
    @Size(min = 3, message = "{customerdetails.citySender.size}")
    private String citySender;
    @Size(min = 3, message = "{customerdetails.streetSender.size}")
    private String streetSender;
    @Pattern(regexp = "\\d{9}", message = "{customerdetails.contactSender.pattern}")
    private String contactSender;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
