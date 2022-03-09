package com.haufe.test.beer.catalogue.mapper;

import com.haufe.test.beer.catalogue.domain.manufacturer.Manufacturer;
import com.haufe.test.beer.catalogue.domain.manufacturer.ManufacturerDTO;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerDTOMapper implements Mapper<Manufacturer, ManufacturerDTO> {
  @Override
  public ManufacturerDTO map(Manufacturer manufacturer) {
    return new ManufacturerDTO(
        manufacturer.getId(),
        manufacturer.getName(),
        manufacturer.getNationality()
    );
  }
}
