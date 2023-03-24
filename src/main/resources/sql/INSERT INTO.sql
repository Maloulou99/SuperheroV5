
INSERT INTO city(city)
values ("Gotham"), ("New York"), ("Themyscira"), ("Copenhagen");

INSERT INTO superpower(superpower)
values ("Intelligence"), ("Stealth"), ("Super-Spider"), ("Strength"), ("Super-Fast"), ("Super-Hearing");

INSERT INTO superhero(hero_name, real_name, creation_year, city_id)
values 
("Batman", "Bruce Wayne", "1939-05-27", 10),
("Spiderman", "Peter Parker", "2002-06-29", 11),
("WonderWoman", "Prinsesse Diana", "1941-12-01", 12);

-- Batman
INSERT INTO superhero_superpower
values 
(100, 1000), (100, 1001);

-- Spiderman
Insert INTO superhero_superpower
values
(101, 1002), (101, 1003);

-- Wonderwoman
INSERT INTO superhero_superpower
values 
(102, 1003), (102, 1004), (102, 1005);


