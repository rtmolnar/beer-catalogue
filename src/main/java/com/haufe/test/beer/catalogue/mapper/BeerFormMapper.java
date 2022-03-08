package com.haufe.test.beer.catalogue.mapper;

import com.haufe.test.beer.catalogue.domain.beer.Beer;
import com.haufe.test.beer.catalogue.domain.beer.BeerForm;
import com.haufe.test.beer.catalogue.domain.manufacturer.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeerFormMapper implements Mapper<BeerForm, Beer>{

//  @Autowired
//  private ManufacturerService manufacturerService;

  @Override
  public Beer map(BeerForm beerForm) {

    Manufacturer manufacturer = new Manufacturer(1l, "Duvel", "Belgian");

    return new Beer(
        beerForm.getName(),
        beerForm.getType(),
        beerForm.getGraduation(),
        beerForm.getFabricationDate(),
        beerForm.getDescription(),
//      TODO: implement manufacturer logic
//       manufacturerService.get(beerForm.getManufacturerId())
        manufacturer
    );
  }
}
