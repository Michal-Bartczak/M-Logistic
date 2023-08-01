package pl.coderslab.magazyn.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
@Setter
@Getter
@Entity
@Data
@Table(name = "drivers")
public class Driver extends BaseUser{
   private String name;
   private String surname;
   private int permission = 2;
}
