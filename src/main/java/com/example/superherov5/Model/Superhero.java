package com.example.superherov5.Model;

import com.example.superherov5.DTO.CitiesDTO;
import com.example.superherov5.DTO.SuperpowerDTO;

import java.util.ArrayList;
import java.util.List;

public class Superhero {
    private int id;
    private String hero_Name;
    private String real_Name;
    private String creation_year;
    private SuperpowerDTO superpowerDTO;
    private CitiesDTO citiesDTO;

    public Superhero() {
        this.superpowerDTO = new SuperpowerDTO();
        this.citiesDTO = new CitiesDTO();
    }

    public Superhero(int id, String heroName, String realName, String creationYear) {
        this.id = id;
        this.hero_Name = heroName;
        this.real_Name = realName;
        this.creation_year = creationYear;
        this.superpowerDTO = new SuperpowerDTO(heroName, new ArrayList<>());
        this.citiesDTO = new CitiesDTO();
    }

    public Superhero(int id, String name, List<Integer> superpowers) {
        this.id = id;
        this.hero_Name = name;
        this.superpowerDTO = new SuperpowerDTO();
    }

    public Superhero(String heroName, String realName, String creation_year) {
        this.hero_Name = heroName;
        this.real_Name = realName;
        this.creation_year = creation_year;
        this.superpowerDTO = new SuperpowerDTO(heroName, new ArrayList<>());
        this.citiesDTO = new CitiesDTO();
    }

    public Superhero(String hero_name, String real_name) {
        this.hero_Name = hero_name;
        this.real_Name = real_name;
        this.superpowerDTO = new SuperpowerDTO(hero_name, new ArrayList<>());
        this.citiesDTO = new CitiesDTO();
    }

    public Superhero(String heroName) {
        this.hero_Name = heroName;
        this.superpowerDTO = new SuperpowerDTO(heroName, new ArrayList<>());
        this.citiesDTO = new CitiesDTO();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHero_Name() {
        return hero_Name;
    }

    public void setHero_Name(String hero_Name) {
        this.hero_Name = hero_Name;
    }

    public String getReal_Name() {
        return real_Name;
    }

    public void setReal_Name(String real_Name) {
        this.real_Name = real_Name;
    }

    public String getCreation_year() {
        return creation_year;
    }

    public void setCreation_year(String creation_year) {
        this.creation_year = creation_year;
    }

    public SuperpowerDTO getSuperpowerDTO() {
        return superpowerDTO;
    }

    public void setSuperpowerDTO(SuperpowerDTO superpowerDTO) {
        this.superpowerDTO = superpowerDTO;
    }

    public CitiesDTO getCitiesDTO() {
        return citiesDTO;
    }

    public void setCitiesDTO(CitiesDTO citiesDTO) {
        this.citiesDTO = citiesDTO;
    }

    public String getSuperpower() {
        if (superpowerDTO != null) {
            return String.join(",", superpowerDTO.getSuperPowers());
        } else {
            return "";
        }
    }

    public void addSuperpower(String superpower) {
        if (superpowerDTO == null) {
            superpowerDTO = new SuperpowerDTO(hero_Name, new ArrayList<>());
        }
        superpowerDTO.addSuperPower(superpower);
    }


    public void removeSuperpower(String superpower) {
        if (superpowerDTO != null) {
            superpowerDTO.removeSuperPower(superpower);
        }
    }

    public void addCity(String city) {
        if (citiesDTO == null) {
            citiesDTO = new CitiesDTO();
        }
        citiesDTO.addCity(city);
    }



    @Override
    public String toString() {
        return "Hero Name: " + hero_Name + "\n" +
                "Real Name: " + real_Name + "\n" +
                "Creation year: " + creation_year;
    }


}
