package pl.coderslab.magazyn.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import pl.coderslab.magazyn.repository.AdminRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AdminTest {
    private Validator validator;
    @Mock
    private AdminRepository adminRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }

    @Test
    public void testValidAdmin() {
        Admin admin = new Admin();
        admin.setUsername("adminuser");
        admin.setEmail("admin@example.com");
        admin.setPassword("password123");

        Set<ConstraintViolation<Admin>> violations = validator.validate(admin);
        assertEquals(0, violations.size());
    }

    @Test
    public void testUsernameToShort() {
        Admin admin = new Admin();
        admin.setUsername("a");   // username to short
        admin.setEmail("admin@example.com");
        admin.setPassword("password123");

        Set<ConstraintViolation<Admin>> violations = validator.validate(admin);
        assertEquals(1, violations.size());

    }

    @Test
    public void testUsernameDuplicate() {
        Admin admin1 = new Admin();
        admin1.setUsername("admin123");
        admin1.setEmail("admin1@example.com");
        admin1.setPassword("password123");

        Admin admin2 = new Admin();
        admin2.setUsername("admin123");
        admin2.setEmail("admin2@example.com");
        admin2.setPassword("password123");

        Set<ConstraintViolation<Admin>> violations1 = validator.validate(admin1);
        Set<ConstraintViolation<Admin>> violations2 = validator.validate(admin2);

        assertEquals(0, violations1.size());
        assertEquals(0, violations2.size());
    }

    @Test
    public void testDuplicateUsername() {
        Admin admin1 = new Admin();
        admin1.setUsername("admin1");
        admin1.setEmail("test@example.com");
        admin1.setPassword("password123");
        adminRepository.save(admin1);

        Admin admin2 = new Admin();
        admin2.setUsername("admin1");   // Duplicate username
        admin2.setEmail("test1@example.com");
        admin2.setPassword("password456");

        when(adminRepository.save(admin2)).thenThrow(DataIntegrityViolationException.class);

        assertThrows(DataIntegrityViolationException.class, () -> adminRepository.save(admin2));

    }

    @Test
    public void testEmailNull() {
        Admin admin = new Admin();
        admin.setUsername("admin1");
        admin.setPassword("password123");

        Set<ConstraintViolation<Admin>> violations = validator.validate(admin);
        assertEquals(1, violations.size());
    }

    @Test
    public void testDuplicateEmail() {
        Admin admin1 = new Admin();
        admin1.setUsername("admin1");
        admin1.setEmail("duplicate@example.com");
        admin1.setPassword("password123");
        adminRepository.save(admin1);

        Admin admin2 = new Admin();
        admin2.setUsername("admin2");
        admin2.setEmail("duplicate@example.com");  // Duplicate email
        admin2.setPassword("password456");

        when(adminRepository.save(admin2)).thenThrow(DataIntegrityViolationException.class);

        assertThrows(DataIntegrityViolationException.class, () -> adminRepository.save(admin2));

    }

    @Test
    public void testPasswordToShort() {
        Admin admin1 = new Admin();
        admin1.setUsername("admin1");
        admin1.setEmail("duplicate@example.com");
        admin1.setPassword("pass");

        Set<ConstraintViolation<Admin>> violations = validator.validate(admin1);
        assertEquals(1, violations.size());

    }
}
