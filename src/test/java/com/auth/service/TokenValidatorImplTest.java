package com.auth.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TokenValidatorImplTest {

  TokenValidator tokenValidator = new TokenValidatorImpl();

  @Test
  public void shouldReturnValidToken_whenIdAndSubjectAreSame() {
    String token = new TokenGeneratorImpl().token("id", "special-name");
    boolean isValid = tokenValidator.validate("id", "special-name", token);

    assertTrue(isValid);
  }

  @Test
  public void shouldReturnValidToken_whenSubjectAreNotSame() {
    String token = new TokenGeneratorImpl().token("id", "name");
    boolean isValid = tokenValidator.validate("id", "special-name", token);

    assertFalse(isValid);
  }

  @Test
  public void shouldReturnValidToken_whenIdAreNotSame() {
    String token = new TokenGeneratorImpl().token("other-id", "special-name");
    boolean isValid = tokenValidator.validate("id", "special-name", token);

    assertFalse(isValid);
  }
}
