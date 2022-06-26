package com.auth.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;

import com.auth.model.AuthToken;
import com.auth.model.AuthTokenResponse;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    private AuthController authController;
    private final AuthTokenResponse forbiddenResponse = new AuthTokenResponse(HttpStatus.FORBIDDEN, new AuthToken());

    @BeforeEach
    public void setUp() {
         authController = new AuthController();
    }

    @Test
    public void authShouldHaveHealthCheck() throws Exception {
        assertEquals("Server is running", authController.check());
    }

    @Test
    public void shouldReturnAuthTokenResponse_whenGetTokenIsCalled() throws Exception {
        // this is mocking the values
        System.out.println(forbiddenResponse.equals(authController.getToken()));
        assertEquals(forbiddenResponse, authController.getToken());
    }

    @Test
    public void shouldValidateToken_whenValidateIsCalled() throws Exception {
    assertEquals(forbiddenResponse, authController.validate());
    }
}
