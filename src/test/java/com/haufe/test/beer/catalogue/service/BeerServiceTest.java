package com.haufe.test.beer.catalogue.service;

import com.haufe.test.beer.catalogue.domain.beer.Beer;
import com.haufe.test.beer.catalogue.repository.BeerPunkAPInRepository;
import com.haufe.test.beer.catalogue.repository.BeerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeerServiceTest {

  @Autowired
  private BeerService beerService;

  @MockBean
  private BeerRepository beerRepository;

  @MockBean
  private BeerPunkAPInRepository beerPunkAPInRepository;

  @Test
  public void should_get_beer_by_id() {

    String beerName = "mock-beer";
    when(beerRepository.findById(any())).thenReturn(Optional.of(new Beer(
      beerName,
      null,
      null,
      null,
      null,
      null
    )));

//    Mockito.atLeast(1).verify();

    assertEquals(beerService.getById(1l).getName(), beerName);
  }

  @Test
  public void should_call_punkapi_if_not_found() {

    when(beerRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(
      Page.empty()
    );

    when(beerPunkAPInRepository.getBeerList(
      anyString(),
      anyFloat(),
      anyString(),
      anyString(),
      anyInt(),
      anyInt())).thenReturn(Collections.emptyList());

    beerService.getBeerListByAttributes(
      "punk",
      null,
      1f,
      "2022-03-13",
      "",
      "",
      Pageable.ofSize(5));

   verify(beerPunkAPInRepository, times(1)).getBeerList(
     anyString(),
     anyFloat(),
     anyString(),
     anyString(),
     anyInt(),
     anyInt()
   );
  }
}
