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

  public Manufacturer() {}

  public Manufacturer(String name, String nationality) {
    this.name = name;
    this.nationality = nationality;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }
}
