package com.haufe.test.beer.catalogue.mapper;

import com.haufe.test.beer.catalogue.domain.beer.Beer;
import com.haufe.test.beer.catalogue.domain.beerpunkapi.BeerPunkAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.haufe.test.beer.catalogue.utils.Utils.getDate;

@Component
public class BeerPunkAPIMapper implements Mapper<BeerPunkAPI, Beer> {

  @Value("${beer.catalogue.punkapi.datePattern}")
  private String datePatternPunkApi;

  @Override
  public Beer map(BeerPunkAPI beerPunkAPI) {
    return new Beer(
      beerPunkAPI.getName(),
      null,
      beerPunkAPI.getAbv(),
      getDate(beerPunkAPI.getFirst_brewed(), datePatternPunkApi),
      beerPunkAPI.getDescription(),
      null
    );
  }
}
