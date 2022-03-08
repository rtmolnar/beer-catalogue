package com.haufe.test.beer.catalogue.mapper;

public interface Mapper <T, U>{
  U map(T t);
}
