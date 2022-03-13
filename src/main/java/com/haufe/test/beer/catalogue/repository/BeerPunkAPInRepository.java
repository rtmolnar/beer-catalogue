package com.haufe.test.beer.catalogue.repository;

import com.haufe.test.beer.catalogue.domain.beerpunkapi.BeerPunkAPI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
  name = "BeerFeignRepository",
  url = "${beer.catalogue.punkapi.url}"
)
public interface BeerPunkAPInRepository {

  @GetMapping(path = "/v2/beers", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<BeerPunkAPI> getBeerList(
    @RequestParam(value = "beer_name") String name,
    @RequestParam (value = "abc") Float abv,
    @RequestParam (value = "brewed_after") String firstBrewed,
    @RequestParam (value = "description") String description,
    @RequestParam (value = "page") int page,
    @RequestParam (value = "per_page") int perPage
  );
}
