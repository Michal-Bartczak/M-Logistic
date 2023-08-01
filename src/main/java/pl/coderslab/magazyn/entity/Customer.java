package pl.coderslab.magazyn.entity;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Setter
@Getter
@Table(name = "customers")
public class Customer extends BaseUser {
    private int permission =1;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)

    private CustomerDetails customerDetails;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

}
