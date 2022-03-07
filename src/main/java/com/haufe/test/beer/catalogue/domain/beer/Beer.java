package com.haufe.test.beer.catalogue.domain.beer;

import com.haufe.test.beer.catalogue.domain.manufacturer.Manufacturer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(
    name = "Beer"
)
public class Beer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotNull(message = "Beer name can not be null!")
  private String name;

  @Column(nullable = false)
  @NotNull(message = "Beer type can not be null!")
  @Enumerated(EnumType.STRING)
  private BeerType type;

  @Column(nullable = false)
  @NotNull(message = "Beer graduation can not be null!")
  private Double graduation;

  @Column(nullable = false)
  @NotNull(message = "Fabrication date can not be null!")
  private LocalDate fabricationDate;

  private String description;

  @ManyToOne(fetch = FetchType.EAGER)
  private Manufacturer manufacturer;

}
