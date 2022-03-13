package com.haufe.test.beer.catalogue.mapper;

import com.haufe.test.beer.catalogue.domain.beer.Beer;
import com.haufe.test.beer.catalogue.domain.beer.BeerForm;
import com.haufe.test.beer.catalogue.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeerFormMapper implements Mapper<BeerForm, Beer> {

  @Autowired
  private ManufacturerService manufacturerService;

  @Override
  public Beer map(BeerForm beerForm) {

    return new Beer(
      beerForm.getName(),
      beerForm.getType(),
      beerForm.getGraduation(),
      beerForm.getFabricationDate(),
      beerForm.getDescription(),
      manufacturerService.getById(beerForm.getManufacturerId())
    );
  }
}
