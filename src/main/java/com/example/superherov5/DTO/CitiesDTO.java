package com.example.superherov5.DTO;

import java.util.ArrayList;
import java.util.List;

public class CitiesDTO {
    private String name;
    private List<String> cities;

    public CitiesDTO(String name, List<String> cities) {
        this.name = name;
        this.cities = cities;
    }

    public CitiesDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "CitiesDTO{" +
                "name='" + name + '\'' +
                ", cities=" + cities +
                '}';
    }
}
