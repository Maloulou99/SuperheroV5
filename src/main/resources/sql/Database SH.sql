DROP DATABASE IF EXISTS superherodatabase;
CREATE DATABASE IF NOT EXISTS superherodatabase;
use superherodatabase;

CREATE TABLE city(
	city_id INT NOT NULL AUTO_INCREMENT,
	city VARCHAR(50) UNIQUE,
    PRIMARY KEY(city_id)
);

CREATE TABLE superhero(
	superhero_id INT NOT NULL AUTO_INCREMENT,
    hero_name VARCHAR(30) UNIQUE,
    real_name VARCHAR(30),
    creation_year VARCHAR(50),
    city_id INT,
    PRIMARY KEY(superhero_id),
    FOREIGN KEY (city_id) REFERENCES city (city_id)
);
CREATE TABLE superpower(
	superpower_id INT NOT NULL AUTO_INCREMENT Primary key,
    superpower VARCHAR(50) NOT NULL UNIQUE,
    hero_id INT,
    FOREIGN KEY (hero_id) REFERENCES superhero(superhero_id)
);

CREATE TABLE superhero_superpower (
	hero_id INT,
	superpower_id INT NOT NULL,
    Primary key (hero_id, superpower_id),
    FOREIGN KEY (superpower_id) REFERENCES superpower(superpower_id),
    FOREIGN KEY (hero_id) REFERENCES superhero(superhero_id)
);


ALTER TABLE city AUTO_INCREMENT = 10;
ALTER TABLE superhero AUTO_INCREMENT = 100;
ALTER TABLE superpower AUTO_INCREMENT = 1000;
