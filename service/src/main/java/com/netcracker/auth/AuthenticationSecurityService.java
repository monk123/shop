package com.netcracker.auth;

/**
 * Service for Security
 *
 * @author Ayupov Vadim
 */

public interface AuthenticationSecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
