package com.auth.controller;

import com.auth.model.AuthToken;
import com.auth.model.AuthTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller is for auth It can tell the health of the server It generates token for users It
 * also validates any jwt token
 */
// write open api annotation for this java class
@RestController
@RequestMapping("/auth")
public class AuthController {

  @GetMapping("/health")
  public String check() {
    return "Server is running";
  }

  /**
   * If user is present in db, then generate a token and return it If user does NOT exist, return
   * Forbidden
   */
  @GetMapping("/get-token")
  public AuthTokenResponse getToken() {
    // if user exists in db, then generate a token and send
    // if user doesn't exist, return not_found
    return new AuthTokenResponse(HttpStatus.FORBIDDEN, new AuthToken());
  }

  @PostMapping("/validate-token")
  public AuthTokenResponse validate() {
    return new AuthTokenResponse(HttpStatus.FORBIDDEN, new AuthToken());
  }
}
