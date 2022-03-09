package com.haufe.test.beer.catalogue.controller;

import com.haufe.test.beer.catalogue.domain.manufacturer.ManufacturerDTO;
import com.haufe.test.beer.catalogue.domain.manufacturer.ManufacturerForm;
import com.haufe.test.beer.catalogue.service.ManufacturerService;
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
@RequestMapping("manufacturers")
public class ManufacturerController {

  @Autowired
  private ManufacturerService manufacturerService;

  @GetMapping
  public Page<ManufacturerDTO> getList(
//      TODO: check why default sort param is not working
      @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pagination
  ) {
    return manufacturerService.getManufacturerList(pagination);
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<ManufacturerDTO> getById(@PathVariable Long id){
    return ResponseEntity.ok(manufacturerService.getDTOById(id));
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<ManufacturerDTO> getByName(@PathVariable String name){
    return ResponseEntity.ok(manufacturerService.getByName(name));
  }

  @PostMapping
  @Transactional
  public ResponseEntity<ManufacturerDTO> create(
      @RequestBody @Valid ManufacturerForm manufacturerForm,
      UriComponentsBuilder uriBuilder
  ){
    ManufacturerDTO manufacturerDTO = manufacturerService.create(manufacturerForm);
    URI uri = uriBuilder.path("/manufacturers/${manufacturerDTO.id}").build().toUri();
    return ResponseEntity.created(uri).body(manufacturerDTO);
  }

  @PutMapping
  @Transactional
  public ResponseEntity<ManufacturerDTO> update( @RequestBody @Valid ManufacturerDTO manufacturerDTO){
    ManufacturerDTO manufacturerDTOUpdate =  manufacturerService.update(manufacturerDTO);
    return ResponseEntity.ok(manufacturerDTOUpdate);
  }

//  TODO: figure out how to handle Referential integrity constraint violation: "FKJ6WDY86CF79G7OO9NV2XGFWAX: PUBLIC.BEER FOREIGN KEY(MANUFACTURER_ID) REFERENCES PUBLIC.MANUFACTURER(ID) (1)"; SQL statement:
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id){
    manufacturerService.delete(id);
  }
}
