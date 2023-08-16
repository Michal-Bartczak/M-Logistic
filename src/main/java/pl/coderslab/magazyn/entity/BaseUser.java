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
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "entity_seq")
    @TableGenerator(
            name = "entity_seq",
            table = "SEQUENCE_TABLE",
            pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_VALUE",
            pkColumnValue = "BASE_USER_SEQ",
            allocationSize = 1
    )
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
