package com.haufe.test.beer.catalogue.service;

import com.haufe.test.beer.catalogue.domain.beer.Beer;
import com.haufe.test.beer.catalogue.domain.beer.BeerDTO;
import com.haufe.test.beer.catalogue.domain.beer.BeerForm;
import com.haufe.test.beer.catalogue.domain.manufacturer.Manufacturer;
import com.haufe.test.beer.catalogue.mapper.BeerDTOMapper;
import com.haufe.test.beer.catalogue.mapper.BeerFormMapper;
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
  private BeerFormMapper beerFormMapper;

  @Autowired
  private BeerDTOMapper beerDTOMapper;

  public Page<BeerDTO> getBeerList(Pageable pagination) {
    Page<Beer> beerList = beerRepository.findAll(pagination);
    return beerList.map(beer -> beerDTOMapper.map(beer));
  }

  public BeerDTO getById(Long id) {
//    TODO: implement notFoundException
    Beer beer = beerRepository.findById(id).get();
    return beerDTOMapper.map(beer);
  }

  public BeerDTO getByName(String name) {
    Beer beer = beerRepository.findByName(name);
    return beerDTOMapper.map(beer);
  }

  public BeerDTO create(BeerForm beerForm) {
    Beer beer = beerFormMapper.map(beerForm);
    return beerDTOMapper.map(beerRepository.save(beer));
  }

  public BeerDTO update(BeerDTO beerDTO) {
//    TODO: implement notFoundException
    Beer beer = beerRepository.getById(beerDTO.getId());

//    TODO: implement search of real manufacturer based on it's id
    Manufacturer manufacturer = new Manufacturer(1l, "Duvel", "Belgian");

    beer.setName(beerDTO.getName());
    beer.setType(beerDTO.getType());
    beer.setGraduation(beerDTO.getGraduation());
    beer.setFabricationDate(beerDTO.getFabricationDate());
    beer.setDescription(beerDTO.getDescription());
    beer.setManufacturer(manufacturer);

    return beerDTOMapper.map(beer);
  }

  public void delete(Long id){
    beerRepository.deleteById(id);
  }
}
