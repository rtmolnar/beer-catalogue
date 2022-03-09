package com.haufe.test.beer.catalogue.service;

import com.haufe.test.beer.catalogue.domain.manufacturer.Manufacturer;
import com.haufe.test.beer.catalogue.domain.manufacturer.ManufacturerDTO;
import com.haufe.test.beer.catalogue.domain.manufacturer.ManufacturerForm;
import com.haufe.test.beer.catalogue.mapper.ManufacturerDTOMapper;
import com.haufe.test.beer.catalogue.mapper.ManufacturerFormMapper;
import com.haufe.test.beer.catalogue.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerService {

  @Autowired
  private ManufacturerRepository manufacturerRepository;

  @Autowired
  private ManufacturerDTOMapper manufacturerDTOMapper;

  @Autowired
  private ManufacturerFormMapper manufacturerFormMapper;

  public Page<ManufacturerDTO> getManufacturerList(Pageable pagination) {
    Page<Manufacturer> manufacturerList = manufacturerRepository.findAll(pagination);
    return manufacturerList.map(manufacturer -> manufacturerDTOMapper.map(manufacturer));
  }

  public ManufacturerDTO getDTOById(Long id){
    return manufacturerDTOMapper.map(getById(id));
  }

  public ManufacturerDTO getByName(String name){
    Manufacturer manufacturer = manufacturerRepository.findByName(name);
    return manufacturerDTOMapper.map(manufacturer);
  }

  public ManufacturerDTO create(ManufacturerForm manufacturerForm){
    Manufacturer manufacturer = manufacturerFormMapper.map(manufacturerForm);
    return manufacturerDTOMapper.map(manufacturerRepository.save(manufacturer));
  }

  public ManufacturerDTO update(ManufacturerDTO manufacturerDTO){
    Manufacturer manufacturer = manufacturerRepository.findById(manufacturerDTO.getId()).get();
    manufacturer.setName(manufacturerDTO.getName());
    manufacturer.setNationality(manufacturerDTO.getNationality());
    return manufacturerDTOMapper.map(manufacturer);
  }

  public void delete(Long id){ manufacturerRepository.deleteById(id);}

  public Manufacturer getById(Long id){
    //    TODO: implement notFoundException
    return manufacturerRepository.findById(id).get();
  }
}
