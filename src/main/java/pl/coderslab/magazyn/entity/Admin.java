package pl.coderslab.magazyn.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Setter
@Getter
@Entity
@Data
@Table(name = "admins")
public class Admin extends BaseUser {

    private int permission = 4;
}
