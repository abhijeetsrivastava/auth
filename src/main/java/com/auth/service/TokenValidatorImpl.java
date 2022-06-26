package com.auth.service;

import com.auth.constant.AppConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Service;

@Service
public class TokenValidatorImpl implements TokenValidator {

  private Claims getClaims(String token) {

    // use apisecret to signin
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(AppConstants.SECRET_KEY);
    Key signingKey =
        new SecretKeySpec(apiKeySecretBytes, AppConstants.signatureAlgorithm.getJcaName());

    return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
  }

  @Override
  public boolean validate(String id, String subject, String token) {
    Claims claims = getClaims(token);
    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);

    return claims.getId().equals(id)
        && claims.getSubject().equals(subject)
        && claims.getExpiration().after(now);
  }
}
