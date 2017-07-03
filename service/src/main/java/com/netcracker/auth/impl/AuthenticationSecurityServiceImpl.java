package com.netcracker.auth.impl;

import com.netcracker.auth.AuthenticationSecurityService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link AuthenticationSecurityService} interface
 *
 * @author Ayupov Vadim
 */

@Log
@Service
public class AuthenticationSecurityServiceImpl implements AuthenticationSecurityService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationSecurityServiceImpl(AuthenticationManager authenticationManager,
                                             UserDetailsService userDetailsService) {

        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    /**
     * поиск зологинененных пользователей
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public String findLoggedInUsername() {
        String username = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            username = authentication.getName();
            log.info(String.format("Successfully %s find ", username));
        }

        return username;
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(authenticationToken);

        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            log.info(String.format("Successfully %s auto logged in", username));
        }
    }
}
