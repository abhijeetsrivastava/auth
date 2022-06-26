package com.auth.service;

import com.auth.constant.AppConstants;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Service;

@Service
public class TokenGeneratorImpl implements TokenGenerator {

  private static final long ONE_HOUR_IN_MILLIS = 3_60_000l;
  // this can come from a private vault

  @Override
  public String token(String id, String subject) {
    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);

    // use apisecret to signin
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(AppConstants.SECRET_KEY);
    Key signingKey =
        new SecretKeySpec(apiKeySecretBytes, AppConstants.signatureAlgorithm.getJcaName());

    // Let's set the JWT Claims
    JwtBuilder builder =
        Jwts.builder()
            .setId(id)
            // add claims
            .setIssuedAt(now)
            .setSubject(subject)
            .setIssuer("root")
            .setExpiration(new Date(nowMillis + ONE_HOUR_IN_MILLIS))
            // cryptographic signining
            .signWith(AppConstants.signatureAlgorithm, signingKey);

    // Builds the JWT and serializes it to a compact, URL-safe string
    return builder.compact();
  }
}
