package pl.coderslab.magazyn.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Setter
@Getter
@Entity
@Data
@Table(name = "employees")
public class Employee extends BaseUser{
    @Size(min=3, message = "Imię musi składać się z conajmniej 3 liter")
    private String name;
    @Size(min=3, max = 15, message = "Nazwisko musi mieć od 3 do 15 znaków")
    private String surname;
    private String role = "EMPLOYEE";


}

