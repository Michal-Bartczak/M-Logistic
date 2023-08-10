package pl.coderslab.magazyn;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;

@Getter
@Setter
@Component
public class RegistrationForm {

   @Size(min = 5, max = 15, message = "Nazwa użytkownika musi mieć od 5 do 15 znaków")
   private String username;

   private String email;
   @Size(min = 5, message = "Hasło musi mieć co najmniej 5 znaków")
   private String password;
   @Size(min = 3, max=15, message = "Twoje imię musi posiadać od 3 do 15 znaków")
   private String name;
   @Size(min = 3, max=15, message = "Twoje imię musi posiadać od 3 do 15 znaków")
   private String surname;
}
