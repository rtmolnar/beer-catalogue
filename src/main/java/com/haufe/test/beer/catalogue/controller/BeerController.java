package com.haufe.test.beer.catalogue.controller;

import com.haufe.test.beer.catalogue.service.BeerService;
import com.haufe.test.beer.catalogue.domain.beer.BeerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("beers")
public class BeerController {

  @Autowired
  private BeerService beerService;

  @GetMapping
  public Page<BeerDTO> getBeerList(
      @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pagination
  ) {
    return beerService.getBeerList(pagination);
  }
}
