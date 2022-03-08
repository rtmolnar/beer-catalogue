
insert into manufacturer(id, name, nationality) values (1, 'Duvel', 'Belgian');

insert into beer (
  id, name, type, graduation, fabrication_date,
  description, manufacturer_id
)
values
  (
    1, 'Duvel 666', 'BLOND', 6.66, now(),
    'The sunny, warm yellow design of the bottles already suggests it. This Duvel is mild, expressive and deliciously drinkable.',
    1
  );

insert into beer (
  id, name, type, graduation, fabrication_date,
  description, manufacturer_id
)
values
  (
    2, 'Duvel Tripple Hop Citra', 'IPA', 9.5, now(),
    'For Duvel Tripel Hop we use the aromatic hop called Citra. This aromatic third hop is grown in the Yakima Valley in Washington and enriches the flavor palate with fresh hints of grapefruit and tropical fruit.',
    1
  );