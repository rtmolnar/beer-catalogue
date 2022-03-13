package com.haufe.test.beer.catalogue.service;

import com.haufe.test.beer.catalogue.domain.beer.Beer;
import com.haufe.test.beer.catalogue.domain.beer.BeerDTO;
import com.haufe.test.beer.catalogue.domain.beer.BeerForm;
import com.haufe.test.beer.catalogue.domain.beer.BeerType;
import com.haufe.test.beer.catalogue.domain.beerpunkapi.BeerPunkAPI;
import com.haufe.test.beer.catalogue.mapper.BeerDTOMapper;
import com.haufe.test.beer.catalogue.mapper.BeerFormMapper;
import com.haufe.test.beer.catalogue.mapper.BeerPunkAPIMapper;
import com.haufe.test.beer.catalogue.repository.BeerPunkAPInRepository;
import com.haufe.test.beer.catalogue.repository.BeerRepository;
import com.haufe.test.beer.catalogue.repository.ManufacturerRepository;
import com.haufe.test.beer.catalogue.specification.BeerSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.haufe.test.beer.catalogue.utils.Utils.getDate;

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
  private BeerPunkAPIMapper beerPunkAPIMapper;

  @Autowired
  private ManufacturerService manufacturerService;

  @Autowired
  private ManufacturerRepository manufacturerRepository;

  @Autowired
  private BeerPunkAPInRepository beerPunkAPInRepository;

  @Value("${beer.catalogue.datePattern}")
  private String datePattern;

  public Page<Beer> getBeerList(Pageable pagination) {
    return beerRepository.findAll(pagination);
  }

  public Beer getDTOById(Long id) {
    return getById(id);
  }

  public Beer getByName(String name) {
    return beerRepository.findByName(name);
  }

  /**
   * Get beer page based on specification. If the first search doesn't return any result it tries to
   * get it in another api using a feign client
   * @param name
   * @param strType
   * @param graduation
   * @param fabricationDateStr
   * @param description
   * @param manufacturerName
   * @param pagination
   *
   * @return
   */
  public Page<Beer> getBeerListByAttributes(
    String name,
    String strType,
    Float graduation,
    String fabricationDateStr,
    String description,
    String manufacturerName,
    Pageable pagination
  ) {

    LocalDate fabricationDate = getDate(fabricationDateStr, datePattern);

    Page<Beer> beerList = beerRepository.findAll(
      Specification.where(
        BeerSpecification.name(name)
          .or(BeerSpecification.type(getType(strType)))
          .or(BeerSpecification.graduation(graduation != null ? graduation : 0))
          .or(BeerSpecification.fabricationDate(fabricationDate))
          .or(BeerSpecification.description(description))
          .or(BeerSpecification.manufacturer(manufacturerName != null ? manufacturerRepository.findByName(manufacturerName) : null))
      ), pagination);

    if (beerList.getTotalElements() <= 0) {
      String punkAPIFabricationDate = fabricationDate != null ?
        fabricationDate.getMonthValue() + "-" + fabricationDate.getYear() : null;

      int pageNumber = pagination.getPageNumber() > 0 ? pagination.getPageNumber() : 1;
      List<BeerPunkAPI> punkAPIBeerList = beerPunkAPInRepository.getBeerList(
        name,
        graduation,
        punkAPIFabricationDate,
        description,
        pageNumber,
        pagination.getPageSize()
      );

      List<Beer> beerFromPunkAPIList = punkAPIBeerList
        .stream()
        .map(beerPunkApi -> beerPunkAPIMapper.map(beerPunkApi)).collect(Collectors.toList());

      beerList = new PageImpl<>(beerFromPunkAPIList, pagination, beerFromPunkAPIList.size());
    }

    return beerList;
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

  private BeerType getType(String type) {
    try {
      return type != null ? BeerType.valueOf(type) : null;
    } catch (IllegalArgumentException e) {
      log.warn(e.getMessage());
      return null;
    }
  }
}
