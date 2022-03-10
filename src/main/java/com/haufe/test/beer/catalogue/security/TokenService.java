package com.haufe.test.beer.catalogue.security;

import com.haufe.test.beer.catalogue.domain.authentication.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
  @Value("${api.jwt.expiration}")
  private int expiration;

  @Value("${api.jwt.secret}")
  private String secret;


  public String generateToken(Authentication authenticate) {
    User user = (User) authenticate.getPrincipal();
    Date nowDate = new Date();
    Date expirationDate = new Date(nowDate.getTime() + expiration);
    return Jwts.builder()
        .setIssuer("beer catalogue API")
        .setSubject(user.getId().toString())
        .setIssuedAt(nowDate)
        .setExpiration(expirationDate)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }


  public boolean isTokenValid(String token) {
    try {
      Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }


  public Long getUserId(String token) {
    Claims tokenBody = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    return Long.parseLong(tokenBody.getSubject());
  }
}
