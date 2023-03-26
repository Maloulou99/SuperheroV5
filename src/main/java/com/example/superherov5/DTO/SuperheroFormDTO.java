package com.example.superherov5.DTO;

import com.example.superherov5.Model.Superhero;

import java.util.List;

public class SuperheroFormDTO {

    private int id;
    private String heroName;
    private String realName;
    private String creationYear;

    private String city;
    private List<String> powerList;

    public SuperheroFormDTO(int id, String heroName, String realName, String creationYear, String city, List<String> powerList) {
        this.id = id;
        this.heroName = heroName;
        this.realName = realName;
        this.creationYear = creationYear;
        this.city = city;
        this.powerList = powerList;
    }

    public SuperheroFormDTO() {
    }

    public SuperheroFormDTO(Superhero superhero) {
        this.id = superhero.getId();
        this.heroName = superhero.getHero_Name();
        this.realName = superhero.getReal_Name();
        this.creationYear = superhero.getCreation_year();
        this.city = superhero.getCitiesDTO().getCityName();
        this.powerList = superhero.getSuperpowerDTO().getSuperPowers();
    }


    public List<String> getPowerList() {
        return powerList;
    }

    public void setPowerList(List<String> powerList) {
        this.powerList = powerList;
    }

    public void add(String power) {
        powerList.add(power);
    }


    public int getId() {
        return id;
    }

    public String getHeroName() {
        return heroName;
    }

    public String getRealName() {
        return realName;
    }

    public String getCreationYear() {
        return creationYear;
    }

    public String getCity() {
        return city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setCreationYear(String creationYear) {
        this.creationYear = creationYear;
    }

    public void setCity(String city) {
        this.city = city;
    }

}

