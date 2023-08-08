package pl.coderslab.magazyn;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException {
        redirectStrategy.sendRedirect(request, response, determineTargetUrl(authentication));
    }

    protected String determineTargetUrl(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ADMIN")) {
                return "/homepage/admin";
            } else if (grantedAuthority.getAuthority().equals("CUSTOMER")) {
                return "/homepage/customer";
            } else if (grantedAuthority.getAuthority().equals("DRIVER")) {
                return "/homepage/driver";
            } else if (grantedAuthority.getAuthority().equals("EMPLOYEE")) {
                return "/homepage/employee";
            }

        }
        throw new IllegalStateException();
    }
}

