package com.haufe.test.beer.catalogue.controller;

import com.haufe.test.beer.catalogue.domain.authentication.LoginDTO;
import com.haufe.test.beer.catalogue.domain.authentication.TokenDTO;
import com.haufe.test.beer.catalogue.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;


  @PostMapping
  public ResponseEntity<?> authenticate(@RequestBody @Valid LoginDTO loginDTO) {

    UsernamePasswordAuthenticationToken loginData = loginDTO.getUserNamePasswordAuthenticationToken();

    try {
      Authentication authenticate = authenticationManager.authenticate(loginData);
      String token = tokenService.generateToken(authenticate);
      return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
    } catch (AuthenticationException e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
