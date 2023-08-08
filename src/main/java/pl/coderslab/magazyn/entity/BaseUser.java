package pl.coderslab.magazyn.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 5, max = 15, message = "Nazwa użytkownika musi mieć od 5 do 15 znaków")
    private String username;
    @NotNull
    @Column(unique = true)
    private String email;

    @Size(min = 5, message = "Hasło musi mieć co najmniej 5 znaków")
    private String password;


}
