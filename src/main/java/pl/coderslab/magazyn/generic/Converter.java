package pl.coderslab.magazyn.generic;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.coderslab.magazyn.exception.PrincipalTypeMismatchException;

@Component
public class Converter {
    public <T> T convertPrincipal(Class<T> type) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (type.isInstance(principal)) {
            return type.cast(principal);
        } else {
            throw new PrincipalTypeMismatchException("Principal nie jest typu: " + type.getSimpleName());
        }
    }
}
