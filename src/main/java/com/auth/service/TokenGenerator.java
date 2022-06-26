package com.auth.service;

/**
 * Generate a token
 */

public interface TokenGenerator {
    public String token(String id, String name);
}

