package com.haufe.test.beer.catalogue.service;

import com.haufe.test.beer.catalogue.domain.beer.Beer;
import com.haufe.test.beer.catalogue.domain.beer.BeerDTO;
import com.haufe.test.beer.catalogue.domain.beer.BeerForm;
import com.haufe.test.beer.catalogue.domain.beer.BeerType;
import com.haufe.test.beer.catalogue.mapper.BeerDTOMapper;
import com.haufe.test.beer.catalogue.mapper.BeerFormMapper;
import com.haufe.test.beer.catalogue.repository.BeerRepository;
import com.haufe.test.beer.catalogue.repository.ManufacturerRepository;
import com.haufe.test.beer.catalogue.specification.BeerSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class BeerService {

  Logger log = LoggerFactory.getLogger(BeerService.class);

  @Autowired
  private BeerRepository beerRepository;

  @Autowired
  private BeerFormMapper beerFormMapper;

  @Autowired
  private BeerDTOMapper beerDTOMapper;

  @Autowired
  private ManufacturerService manufacturerService;

  @Autowired
  private ManufacturerRepository manufacturerRepository;

  @Value("${beer.catalogue.datePattern}")
  private String datePattern;

  public Page<Beer> getBeerList(Pageable pagination) {
    return  beerRepository.findAll(pagination);
  }

  public Beer getDTOById(Long id) {
    return getById(id);
  }

  public Beer getByName(String name) {
    return beerRepository.findByName(name);
  }

  public Page<Beer> getBeerListByAttributes(
    String name,
    String strType,
    Float graduation,
    String fabricationDate,
    String description,
    String manufacturerName,
    Pageable pagination
  ) {

    return beerRepository.findAll(
      Specification.where(
        BeerSpecification.name(name)
        .or(BeerSpecification.type(getType(strType)))
        .or(BeerSpecification.graduation(graduation != null ? graduation : 0))
        .or(BeerSpecification.fabricationDate(getDate(fabricationDate)))
        .or(BeerSpecification.description(description))
        .or(BeerSpecification.manufacturer(manufacturerName != null ? manufacturerRepository.findByName(manufacturerName) : null))
      ), pagination);
  }

  public Beer create(BeerForm beerForm) {
    Beer beer = beerFormMapper.map(beerForm);
    return beerRepository.save(beer);
  }

  public Beer update(BeerDTO beerDTO) {
    Beer beer = getById(beerDTO.getId());

    beer.setName(beerDTO.getName());
    beer.setType(beerDTO.getType());
    beer.setGraduation(beerDTO.getGraduation());
    beer.setFabricationDate(beerDTO.getFabricationDate());
    beer.setDescription(beerDTO.getDescription());
    beer.setManufacturer(manufacturerService.getById(beerDTO.getManufacturerId()));

    return beer;
  }

  public void delete(Long id) {
    beerRepository.deleteById(id);
  }

  public Beer getById(Long id) {
    //    TODO: implement notFoundException
    return beerRepository.findById(id).get();
  }

  private BeerType getType(String type){
    try {
      return type != null ? BeerType.valueOf(type) : null;
    }catch (IllegalArgumentException e) {
      log.warn(e.getMessage());
      return null;
    }
  }

  private LocalDate getDate(String date){
    try{
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
      return date != null ? LocalDate.parse(date, formatter) : null;
    }catch (DateTimeParseException e){
      log.warn(e.getMessage());
      return null;
    }
  }
}
