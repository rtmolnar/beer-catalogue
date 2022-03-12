package com.haufe.test.beer.catalogue.security;

import com.haufe.test.beer.catalogue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthenticationService authenticationService;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private UserRepository adminRepository;


  // Authentication configuration
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
  }

  //  Configure authorization for endpoint access
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
      .antMatchers(HttpMethod.POST, "/auth").permitAll()
      .antMatchers(HttpMethod.GET, "/beers").permitAll()
      .antMatchers(HttpMethod.GET, "/beers/*").permitAll()
      .antMatchers(HttpMethod.GET, "/manufacturers").permitAll()
      .antMatchers(HttpMethod.GET, "/manufacturers/*").permitAll()
      .antMatchers(HttpMethod.POST, "/beer").hasAnyRole("ADMIN", "MANUFACTURER")
      .antMatchers(HttpMethod.POST, "/manufacturer").hasRole("ADMIN")
      .antMatchers(HttpMethod.POST, "/manufacturer/**").hasRole("MANUFACTURER")
      .antMatchers(HttpMethod.PUT, "/beer").hasAnyRole("ADMIN", "MANUFACTURER")
      .antMatchers(HttpMethod.PUT, "/manufacturer").hasRole("ADMIN")
      .antMatchers(HttpMethod.DELETE, "/beer/*").hasRole("ADMIN")
      .antMatchers(HttpMethod.DELETE, "/manufacturer/*").hasRole("ADMIN")
      .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
      .anyRequest().authenticated()
      .and().csrf().disable()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and().addFilterBefore(new TokenAuthenticationFilter(tokenService, adminRepository), UsernamePasswordAuthenticationFilter.class);
  }


  //  Configure authorization for static resources
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
      .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
  }


  @Override
  @Bean
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }
}
