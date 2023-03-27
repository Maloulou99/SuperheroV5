package com.example.superherov5.DTO;

import com.example.superherov5.Model.Superhero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SuperpowerDTO {
    private int id;
    private static int count = 0;
    private String name;
    private String selectedValue;
    private List<String> superPowers;


    public SuperpowerDTO( String name, List<String> superPowers) {
        this.id = ++count;
        this.name = name;
        this.superPowers = superPowers;
    }

    public SuperpowerDTO() {
        this.name = "";
        this.superPowers = new ArrayList<>();
    }


    public SuperpowerDTO(String power) {
        this.name = power;
    }

    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String  selectedValue) {
        this.selectedValue = selectedValue;
    }

    public String getName() {
        return name;
    }

    public List<String> getSuperPowers() {
        return superPowers;
    }
    public void setSuperPowers(List<String> superPowers) {
        this.superPowers = superPowers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSuperPower(String superPower) {
        superPowers.add(superPower);
    }

    public void removeSuperPower(String superPower) {
        superPowers.remove(superPower);
    }

    public List<String> getSuperpowers() {
        return superPowers;
    }



    @Override
    public String toString() {
        return "Name = " + name + ", SuperPowers = " + superPowers;
    }
}
