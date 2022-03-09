package com.haufe.test.beer.catalogue.domain.manufacturer;

public class ManufacturerForm {

  private String name;
  private String nationality;

  public ManufacturerForm() {}

  public ManufacturerForm(String name, String nationality) {
    this.name = name;
    this.nationality = nationality;
  }

  public String getName() {
    return name;
  }

  public String getNationality() {
    return nationality;
  }
}

