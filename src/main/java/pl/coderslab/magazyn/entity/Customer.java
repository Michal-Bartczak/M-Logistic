package pl.coderslab.magazyn.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "customers")

public class Customer extends BaseUser {
    private String role = "CUSTOMER";

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private CustomerDetails customerDetails;
    @JsonManagedReference
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;


}
