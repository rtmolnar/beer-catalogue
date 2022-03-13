package com.haufe.test.beer.catalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BeerCatalogueApplication {

  public static void main(String[] args) {
    SpringApplication.run(BeerCatalogueApplication.class, args);
  }

}
