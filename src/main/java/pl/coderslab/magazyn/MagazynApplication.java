package pl.coderslab.magazyn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.magazyn.entity.Employee;
import pl.coderslab.magazyn.repository.EmployeeRepository;

@SpringBootApplication
public class MagazynApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagazynApplication.class, args);
	}


}

