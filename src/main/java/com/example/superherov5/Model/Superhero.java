package com.example.superherov5.Model;

import com.example.superherov5.DTO.CitiesDTO;
import com.example.superherov5.DTO.SuperpowerDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Superhero {
    private int id;
    private String hero_name;
    private String real_name;
    private String creation_year;
    private SuperpowerDTO superpowers;
    private CitiesDTO cities;

    public Superhero() {
        superpowers = new SuperpowerDTO();
        cities = new CitiesDTO();
    }


    public Superhero(int id, String heroName, String realName, String creationYear) {
        this.id = id;
        this.hero_name = heroName;
        this.real_name = realName;
        this.creation_year = creationYear;
        this.superpowers = new SuperpowerDTO(heroName, new ArrayList<>());
        this.cities = new CitiesDTO();
    }

    public Superhero(int id, String heroName, List<String> superpowers) {
        this.id = id;
        this.hero_name = heroName;
        this.superpowers = new SuperpowerDTO(heroName, superpowers);
        this.cities = new CitiesDTO();
    }

    public Superhero(String heroName, String realName, String creationYear, List<String> superpowers) {
        this.hero_name = heroName;
        this.real_name = realName;
        this.creation_year = creationYear;
        this.superpowers = new SuperpowerDTO(heroName, superpowers);
        this.cities = new CitiesDTO();
    }

    public Superhero(String heroName, String realName) {
        this.hero_name = heroName;
        this.real_name = realName;
        this.superpowers = new SuperpowerDTO(heroName, new ArrayList<>());
        this.cities = new CitiesDTO();
    }

    public Superhero(String heroName) {
        this.hero_name = heroName;
        this.superpowers = new SuperpowerDTO(heroName, new ArrayList<>());
        this.cities = new CitiesDTO();
    }

    public Superhero(int id, String hero_name, String real_Name, String creation_year, int citiesDTO){
    this.id = id;
    this.hero_name = hero_name;
    this.real_name = real_Name;
    this.creation_year = creation_year;

    }

    public Superhero(String hero_name, String real_name, String creation_year) {
        this.hero_name = hero_name;
        this.real_name = real_name;
        this.creation_year = creation_year;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getHero_name() {
        return hero_name;
    }

    public void setHero_name(String hero_name) {
        this.hero_name = hero_name;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getCreation_year() {
        return creation_year;
    }

    public void setCreation_year(String creation_year) {
        this.creation_year = creation_year;
    }

    public List<String> getSuperpowers() {
        return superpowers.getSuperPowers();
    }

    public void setSuperpowers(List<String> superpowers) {
        this.superpowers.setSuperPowers(superpowers);
    }

    public void setSuperpowers(SuperpowerDTO superpowers) {
        this.superpowers = superpowers;
    }

    public void addSuperpower(String superpower) {
        if (superpowers == null) {
            superpowers = new SuperpowerDTO();
        }
        superpowers = new SuperpowerDTO(hero_name, Collections.singletonList(superpower));
    }

    public void removeSuperpower(String superpower) {
        if (superpowers != null) {
            superpowers.getSuperPowers().remove(superpower);
        }
    }

    public CitiesDTO getCities() {
        return cities;
    }

    public void setCities(CitiesDTO cities) {
        this.cities = cities;
    }

    public void addCity(String city) {
        if (cities == null) {
            cities = new CitiesDTO();
        }
        cities.addCity(city);
    }




    @Override
    public String toString() {
        return "Hero Name: " + hero_name + "\n" +
                "Real Name: " + real_name + "\n" +
                "Creation year: " + creation_year;
    }



}
