package com.haufe.test.beer.catalogue.domain.manufacturer;

public class ManufacturerDTO {

  private Long id;
  private String name;
  private String nationality;

  public ManufacturerDTO() {}

  public ManufacturerDTO(Long id, String name, String nationality) {
    this.id = id;
    this.name = name;
    this.nationality = nationality;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getNationality() {
    return nationality;
  }
}
