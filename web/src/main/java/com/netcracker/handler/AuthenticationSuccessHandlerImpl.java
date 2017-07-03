package com.netcracker.handler;

import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

/**
 * Implementation of {@link AuthenticationSuccessHandler}
 *
 * @author Ayupov Vadim
 */

@Log
@Component(value = "authenticationSuccessHandler")
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    private void handle(HttpServletRequest request,
                        HttpServletResponse response,
                        Authentication authentication)
            throws IOException, ServletException {

        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            log.info("Response has already been committed. Unable to redirect to "
                    + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        boolean isUser = false;
        boolean isAdmin = false;

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_USER")) {
                isUser = true;
                break;
            } else if (authority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            }
        }

        if (isUser) {
            return "welcome/1";
        } else if (isAdmin) {
            return "admin/product/list/1";
        } else {
            throw new IllegalStateException();
        }
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

}
