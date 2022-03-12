
insert into profile (id, name) values (1, 'ROLE_ADMIN');
insert into profile (id, name) values (2, 'ROLE_MANUFACTURER');

insert into user (id, username, password) values (1, 'admin', '$2a$10$Q1hueFjGCfd1/2a6aptMeudtKMiSV8FhCHhafx2a8TkyCIIP3GcY6');
insert into user (id, username, password) values (2, 'Duvel', '$2a$10$Q1hueFjGCfd1/2a6aptMeudtKMiSV8FhCHhafx2a8TkyCIIP3GcY6');
insert into user (id, username, password) values (3, 'Bodebrown', '$2a$10$Q1hueFjGCfd1/2a6aptMeudtKMiSV8FhCHhafx2a8TkyCIIP3GcY6');

insert into user_profiles (user_id, profiles_id) values (1, 1);
insert into user_profiles (user_id, profiles_id) values (2, 2);
insert into user_profiles (user_id, profiles_id) values (3, 2);


insert into manufacturer(id, name, nationality) values (1, 'Duvel', 'Belgian');
insert into manufacturer(id, name, nationality) values (2, 'Bodebrown', 'Brasilian');

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