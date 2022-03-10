package com.haufe.test.beer.catalogue.domain.authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(
    name = "user"
)
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotNull(message = "Username can not be null!")
  private String username;

  @Column(nullable = false)
  @NotNull(message = "Password can not be null!")
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Profile> profiles = new ArrayList<>();


  public User(Long id, String username, String password) {
    this.username = username;
    this.password = password;
  }

  public User() {
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getUsername() {
    return username;
  }


  @Override
  public boolean isAccountNonExpired() {
    return true;
  }


  @Override
  public boolean isAccountNonLocked() {
    return true;
  }


  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }


  @Override
  public boolean isEnabled() {
    return true;
  }


  public void setUsername(String username) {
    this.username = username;
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.profiles;
  }


  public String getPassword() {
    return password;
  }


  public void setPassword(String password) {
    this.password = password;
  }
}
