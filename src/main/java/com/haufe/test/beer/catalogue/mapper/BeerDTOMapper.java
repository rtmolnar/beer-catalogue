package com.haufe.test.beer.catalogue.mapper;

import com.haufe.test.beer.catalogue.domain.beer.Beer;
import com.haufe.test.beer.catalogue.domain.beer.BeerDTO;
import org.springframework.stereotype.Component;

@Component
public class BeerDTOMapper implements Mapper<Beer, BeerDTO> {
  @Override
  public BeerDTO map(Beer beer) {
    return new BeerDTO(
        beer.getId(),
        beer.getName(),
        beer.getType(),
        beer.getGraduation(),
        beer.getFabricationDate(),
        beer.getDescription(),
        beer.getManufacturer().getId()
    );
  }
}
