package com.haufe.test.beer.catalogue.controller;

import com.haufe.test.beer.catalogue.domain.beer.BeerForm;
import com.haufe.test.beer.catalogue.service.BeerService;
import com.haufe.test.beer.catalogue.domain.beer.BeerDTO;
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
  public Page<BeerDTO> getList(
//      TODO: check why default sort param is not working
      @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pagination
  ) {
    return beerService.getBeerList(pagination);
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<BeerDTO> getById(@PathVariable Long id){
    return ResponseEntity.ok(beerService.getDTOById(id));
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<BeerDTO> getByName(@PathVariable String name){
    return ResponseEntity.ok(beerService.getByName(name));
  }

  @PostMapping
  @Transactional
  public ResponseEntity<BeerDTO> create(
      @RequestBody @Valid BeerForm beerForm,
      UriComponentsBuilder uriBuilder
  ){
    BeerDTO beerDTO = beerService.create(beerForm);
    URI uri = uriBuilder.path("/beers/${beerDTO.id}").build().toUri();
    return ResponseEntity.created(uri).body(beerDTO);
  }

  @PutMapping
  @Transactional
  public ResponseEntity<BeerDTO> update( @RequestBody @Valid BeerDTO beerDTO){
    BeerDTO beerDTOUpdate =  beerService.update(beerDTO);
    return ResponseEntity.ok(beerDTOUpdate);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id){
    beerService.delete(id);
  }
}
