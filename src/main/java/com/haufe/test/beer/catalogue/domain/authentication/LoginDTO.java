package com.haufe.test.beer.catalogue.domain.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginDTO {
  private String userName;
  private String password;


  public String getUserName() {
    return userName;
  }


  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getPassword() {
    return password;
  }


  public void setPassword(String password) {
    this.password = password;
  }


  public UsernamePasswordAuthenticationToken getUserNamePasswordAuthenticationToken() {
    return new UsernamePasswordAuthenticationToken(userName, password);
  }
}
