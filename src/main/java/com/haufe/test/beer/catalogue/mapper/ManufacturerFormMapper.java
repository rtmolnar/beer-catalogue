package com.haufe.test.beer.catalogue.mapper;

import com.haufe.test.beer.catalogue.domain.manufacturer.Manufacturer;
import com.haufe.test.beer.catalogue.domain.manufacturer.ManufacturerForm;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerFormMapper implements Mapper<ManufacturerForm, Manufacturer> {


  @Override
  public Manufacturer map(ManufacturerForm manufacturerForm) {
    return new Manufacturer(
        manufacturerForm.getName(),
        manufacturerForm.getNationality()
    );
  }
}
