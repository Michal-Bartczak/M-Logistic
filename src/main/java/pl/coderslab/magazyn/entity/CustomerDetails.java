package pl.coderslab.magazyn.entity;

import lombok.*;

import javax.persistence.*;
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
    private String nameCompanySender;

    private String zipCodeSender;

    private String citySender;
    private String streetSender;
    private String contactSender;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
