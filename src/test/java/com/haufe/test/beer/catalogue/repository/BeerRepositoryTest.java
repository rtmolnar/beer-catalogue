package com.haufe.test.beer.catalogue.repository;

import com.haufe.test.beer.catalogue.domain.beer.Beer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BeerRepositoryTest {

  @Autowired
  private BeerRepository beerRepository;

  @Test
  public void should_find_all_beers_and_return_a_page() {

    PageRequest pageable = PageRequest.of(0, 5);
    Page<Beer> beerList = beerRepository.findAll(pageable);

    Assert.assertNotNull(beerList);
    Assert.assertTrue(beerList.getTotalElements() == 2);
  }

  @Test
  public void should_paginate_beer_results() {

    PageRequest pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "name"));
    Page<Beer> beerList = beerRepository.findAll(pageable);


    Assert.assertTrue(beerList.stream().findFirst().isPresent());
    Assert.assertTrue(beerList.getContent().size() == 1);

    Assert.assertTrue(beerList.getContent().get(0).getName().equals("Duvel"));

    pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "name"));
    beerList = beerRepository.findAll(pageable);

    Assert.assertTrue(beerList.getContent().get(0).getName().equals("Vedett"));

  }
}
