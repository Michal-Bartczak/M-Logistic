package pl.coderslab.magazyn.generic;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.coderslab.magazyn.entity.BaseUser;
@Component
public class UserPasswordEncryptor {

    private final BCryptPasswordEncoder passwordEncoder;
    private final PasswordEncoder encoder;

    public UserPasswordEncryptor(BCryptPasswordEncoder passwordEncoder, PasswordEncoder encoder) {
        this.passwordEncoder = passwordEncoder;
        this.encoder = encoder;
    }

    public <T extends BaseUser> T encryptPasswordInBaseUser(T user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
    public String encryptOnlyPassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }
}
