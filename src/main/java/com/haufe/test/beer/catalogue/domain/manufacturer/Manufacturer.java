package com.haufe.test.beer.catalogue.domain.manufacturer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Manufacturer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotNull(message = "Manufacturer name can not be null!")
  private String name;

  @Column(nullable = false)
  @NotNull(message = "Manufacturer nationality can not be null!")
  private String nationality;
}
