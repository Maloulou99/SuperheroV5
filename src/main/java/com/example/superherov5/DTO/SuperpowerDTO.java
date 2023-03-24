package com.example.superherov5.DTO;

import com.example.superherov5.Model.Superhero;

import java.util.ArrayList;
import java.util.List;

public class SuperpowerDTO {
    private String name;
    private int selectedValue;
    private List<String> superPowers;

    public SuperpowerDTO(String name, List<String> superPowers) {
        this.name = name;
        this.superPowers = superPowers;
    }
    public SuperpowerDTO() {
        this.name = "";
        this.superPowers = new ArrayList<String>();
    }

    public int getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(int selectedValue) {
        this.selectedValue = selectedValue;
    }

    public String getName() {
        return name;
    }

    public List<String> getSuperPowers() {
        return superPowers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSuperPowers(List<String> superPowers) {
        this.superPowers = superPowers;
    }

    public void addSuperPower(String superPower) {
        superPowers.add(superPower);
    }

    public void removeSuperPower(String superPower) {
        superPowers.remove(superPower);
    }

    public void printSuperPowers() {
        System.out.println(superPowers);
    }


    @Override
    public String toString() {
        return "Name = " + name + ", SuperPowers = " + superPowers;
    }
}
