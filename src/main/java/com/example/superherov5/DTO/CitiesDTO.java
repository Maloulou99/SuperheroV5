package com.example.superherov5.DTO;

import java.util.ArrayList;
import java.util.List;

public class CitiesDTO {
    private String cityName;
    private int city_id;
    private String selectedValue;
    private List<String> cities = new ArrayList<>();




    public CitiesDTO(String cityName, List<String> cities) {
        this.cityName = cityName;
        this.cities = cities;
    }
    public CitiesDTO() {}

    public int getCity_id() {
        return city_id;
    }
    public void addCity(String city) {
        cities.add(city);
    }
    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public CitiesDTO(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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
                "name='" + cityName + '\'' +
                ", cities=" + cities +
                '}';
    }


}
