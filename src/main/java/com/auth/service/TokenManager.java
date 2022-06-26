package com.auth.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.auth.model.User;

public class TokenManager {

  private final TokenGenerator tokenGenerator;
  private final TokenValidator tokenValidator;

  @Autowired
  public TokenManager(TokenGenerator tokenGenerator, TokenValidator tokenValidator) {
    this.tokenGenerator = tokenGenerator;
    this.tokenValidator = tokenValidator;
  }

  public String getToken(User user) {
    return tokenGenerator.token(user.id(), user.name());
  }

  public boolean isValid(User user, String token) {
    return tokenValidator.validate(user.id(), user.name(), token);
  }
}
