package com.haufe.test.beer.catalogue.domain.beerpunkapi;

public class BeerPunkAPI {

  private String name;
  private Double abv;
  private String first_brewed;
  private String description;
  private String image_url;

  public BeerPunkAPI() {}

  public BeerPunkAPI(String name, Double abv, String first_brewed, String description, String image_url) {
    this.name = name;
    this.abv = abv;
    this.first_brewed = first_brewed;
    this.description = description;
    this.image_url = image_url;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getAbv() {
    return abv;
  }

  public void setAbv(Double abv) {
    this.abv = abv;
  }

  public String getFirst_brewed() {
    return first_brewed;
  }

  public void setFirst_brewed(String first_brewed) {
    this.first_brewed = first_brewed;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImage_url() { return image_url;}

  public void setImage_url(String image_url) {this.image_url = image_url;}
}
