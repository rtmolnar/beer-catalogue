package com.haufe.test.beer.catalogue.service;

import com.haufe.test.beer.catalogue.domain.beer.Beer;
import com.haufe.test.beer.catalogue.domain.beer.BeerDTO;
import com.haufe.test.beer.catalogue.mapper.BeerToDTOMapper;
import com.haufe.test.beer.catalogue.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BeerService {

  @Autowired
  private BeerRepository beerRepository;

  @Autowired
  private BeerToDTOMapper beerToDTOMapper;

  public Page<BeerDTO> getBeerList(Pageable pagination) {
    Page<Beer> beerList = beerRepository.findAll(pagination);
    return beerList.map(beer -> beerToDTOMapper.map(beer));
  }
}
