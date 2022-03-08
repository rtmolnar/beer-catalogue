package com.haufe.test.beer.catalogue.repository;

import com.haufe.test.beer.catalogue.domain.beer.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {

  Page<Beer> findAll(Pageable pagination);

  Beer findByName(String name);
}
