package pl.coderslab.magazyn;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pl.coderslab.magazyn.entity.Customer;
import pl.coderslab.magazyn.entity.Driver;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Component
public class RegistrationForm {
   @Size(min = 5, max = 15, message = "Nazwa użytkownika musi mieć od 5 do 15 znaków")
   private String username;
   @NotNull
   private String email;
   @Size(min = 5, message = "Hasło musi mieć co najmniej 5 znaków")
   private String password;

   private String name;
   private String surname;
}
