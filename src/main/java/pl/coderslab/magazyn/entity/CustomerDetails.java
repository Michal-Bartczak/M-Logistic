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
    @Size(min = 5, max = 30, message = "Nazwa firmy musi być od 5 do 30 znaków")
    private String nameCompanySender;
    @Pattern(regexp = "\\d{2}-\\d{3}", message = "Nieprawidłowy kod pocztowy")
    private String zipCodeSender;
    @Size(min = 3, message = "Nazwa miasta musi się składać conajmniej z 3 liter")
    private String citySender;
    @Size(min = 3, message = "Nazwa ulicy musi się składać conajmniej z 3 liter")
    private String streetSender;
    @Pattern(regexp = "\\d{9}", message = "Nieprawidłowy numer telefonu")
    private String contactSender;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
