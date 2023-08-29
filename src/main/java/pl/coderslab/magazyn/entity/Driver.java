package pl.coderslab.magazyn.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Data
@Table(name = "drivers")
public class Driver extends BaseUser{
   @Size(min=3, message = "{driver.name.size}")
   private String name;
   @Size(min=3, max = 15, message = "{driver.surname.size}")
   private String surname;
   private String role = "DRIVER";
   @JsonManagedReference
   @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<DeliveryLog> logs;
   public void addLog(DeliveryLog log) {
      this.logs.add(log);
      log.setDriver(this);
   }

   public void removeLog(DeliveryLog log) {
      this.logs.remove(log);
      log.setDriver(null);
   }
}
