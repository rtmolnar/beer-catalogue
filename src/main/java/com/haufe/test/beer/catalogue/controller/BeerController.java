package com.haufe.test.beer.catalogue.controller;

import com.haufe.test.beer.catalogue.domain.beer.Beer;
import com.haufe.test.beer.catalogue.domain.beer.BeerDTO;
import com.haufe.test.beer.catalogue.domain.beer.BeerForm;
import com.haufe.test.beer.catalogue.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("beers")
public class BeerController {

  @Autowired
  private BeerService beerService;

  @GetMapping
  public Page<Beer> getList(
//      TODO: check why default sort param is not working
      @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pagination
  ) {
    return beerService.getBeerList(pagination);
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<Beer> getById(@PathVariable Long id){
    return ResponseEntity.ok(beerService.getDTOById(id));
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<Beer> getByName(@PathVariable String name){
    return ResponseEntity.ok(beerService.getByName(name));
  }

  @GetMapping("/attributes")
  public Page<Beer> getByAttributes(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String type,
      @RequestParam(required = false) Float graduation,
      @RequestParam(required = false) String fabricationDate,
      @RequestParam(required = false) String description,
      @RequestParam(required = false) String manufacturerName,
      @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pagination
  ){
    return beerService.getBeerListByAttributes(
        name,
        type,
        graduation,
        fabricationDate,
        description,
        manufacturerName,
        pagination);
  }

  @PostMapping
  @Transactional
  public ResponseEntity<Beer> create(
      @RequestBody @Valid BeerForm beerForm,
      UriComponentsBuilder uriBuilder
  ){
    Beer beer = beerService.create(beerForm);
    URI uri = uriBuilder.path("/beers/${beer.id}").build().toUri();
    return ResponseEntity.created(uri).body(beer);
  }

  @PutMapping
  @Transactional
//  @PostAuthorize("#returnObject.manufacturer.name == authentication.principal.username")
  public ResponseEntity<Beer> update( @RequestBody @Valid BeerDTO beerDTO){
    Beer beerUpdate =  beerService.update(beerDTO);
    return ResponseEntity.ok(beerUpdate);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id){ beerService.delete(id); }
}
