package com.auth.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.auth.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TokenManagerTest {

  @Mock TokenGeneratorImpl mockTokenGenerator;
  @Mock TokenValidatorImpl mockTokenValidator;

  @InjectMocks TokenManager tokenManager;

  @Test
  public void shouldCallTokenGenerator_whenGetToken() {
    User user = new User("id", "user");
    when(mockTokenGenerator.token(user.id(), user.name())).thenReturn("token");
    tokenManager.getToken(user);
    verify(mockTokenGenerator, times(1)).token(user.id(), user.name());
  }

  @Test
  public void shouldCallTokenValidator_whenValidate() {
    User user = new User("id", "user");
    when(mockTokenValidator.validate("id", "user", "token")).thenReturn(true);
    tokenManager.isValid(user, "token");
    verify(mockTokenValidator, times(1)).validate(user.id(), user.name(), "token");
  }
}
