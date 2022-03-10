package com.haufe.test.beer.catalogue.domain.authentication;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(
    name = "profile"
)
public class Profile implements GrantedAuthority
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @Override public String getAuthority()
    {
        return name;
    }
}
