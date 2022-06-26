package com.auth.service;

import org.springframework.stereotype.Service;

import com.auth.model.AuthToken;

@Service
public class TokenGeneratorImpl implements TokenGenerator {

    @Override
    public AuthToken token() {
        // generate token
        
        return null;
    }
}
