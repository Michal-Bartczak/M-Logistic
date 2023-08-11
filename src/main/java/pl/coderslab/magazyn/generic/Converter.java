package pl.coderslab.magazyn.generic;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Converter {
    public <T> T convertPrincipal(Class<T> type) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (type.isInstance(principal)) {
            return type.cast(principal);
        } else {
            throw new IllegalArgumentException("Principal is not of type " + type.getSimpleName());
        }
    }
}
