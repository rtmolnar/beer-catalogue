package com.haufe.test.beer.catalogue.domain.beer;

import java.time.LocalDate;

public class BeerForm {

  private String name;
  private BeerType type;
  private Double graduation;
  private LocalDate fabricationDate;
  private String description;
  private long manufacturerId;

  public BeerForm(String name, BeerType type, Double graduation, LocalDate fabricationDate, String description, long manufacturerId) {
    this.name = name;
    this.type = type;
    this.graduation = graduation;
    this.fabricationDate = fabricationDate;
    this.description = description;
    this.manufacturerId = manufacturerId;
  }

  public String getName() {
    return name;
  }

  public BeerType getType() {
    return type;
  }

  public Double getGraduation() {
    return graduation;
  }

  public LocalDate getFabricationDate() {
    return fabricationDate;
  }

  public String getDescription() {
    return description;
  }

  public long getManufacturerId() {
    return manufacturerId;
  }
}
