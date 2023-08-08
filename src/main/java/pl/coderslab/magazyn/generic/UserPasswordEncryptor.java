package pl.coderslab.magazyn.generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.coderslab.magazyn.entity.BaseUser;
@Component
public class UserPasswordEncryptor {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public <T extends BaseUser> T encryptPassword(T user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
}
