package com.haufe.test.beer.catalogue.specification;

import com.haufe.test.beer.catalogue.domain.beer.Beer;
import com.haufe.test.beer.catalogue.domain.beer.BeerType;
import com.haufe.test.beer.catalogue.domain.manufacturer.Manufacturer;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class BeerSpecification {

  public static Specification<Beer> name(String name){
    return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
  }

  public static Specification<Beer> type(BeerType type){
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type);
  }

  public static Specification<Beer> graduation(float graduation){
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("graduation"), graduation);
  }

  public static Specification<Beer> fabricationDate(LocalDate fabricationDate){
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("fabricationDate"), fabricationDate);
  }

  public static Specification<Beer> description(String description){
    return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("description"), description);
  }

  public static Specification<Beer> manufacturer(Manufacturer manufacturer){
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("manufacturer"), manufacturer);
  }

}
