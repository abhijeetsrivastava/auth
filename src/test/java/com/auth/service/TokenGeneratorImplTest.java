package com.auth.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** TokenGenerator */
public class TokenGeneratorImplTest {

  private TokenGeneratorImpl tokenGeneratorImpl;

  @BeforeEach
  public void setUp() {
    tokenGeneratorImpl = new TokenGeneratorImpl();
  }

  @Test
  public void whenGeneratorIsCalled_shouldReturnAuthToken() {
    String token = tokenGeneratorImpl.token("", "");

    assertNotNull(token);
  }
}
