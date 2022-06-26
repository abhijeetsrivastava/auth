package com.auth.service;

import io.jsonwebtoken.Claims;

public interface TokenValidator {
    public boolean validate(String id, String subject, String token);
}
