package pl.coderslab.magazyn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;

@Getter
@Setter
@Component
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {

   @Size(min = 5, max = 15, message = "Nazwa użytkownika musi mieć od 5 do 15 znaków")
   private String username;

   private String email;
   @Size(min = 5, message = "Hasło musi mieć co najmniej 5 znaków")
   private String password;

   private String name;

   private String surname;
}
