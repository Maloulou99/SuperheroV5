package com.example.superherov5.Model;

public class Superhero {
    private int id;
    private String hero_Name;
    private String real_Name;
    private String creation_year;

    //private String selectedValue;

    public Superhero(int id, String heroName, String realName, String creationYear) {
        this.id = id;
        this.hero_Name = heroName;
        this.real_Name = realName;
        this.creation_year = creationYear;
    }

    public Superhero(){

    }

    public Superhero(String heroName, String realName, String creation_year) {
        this.hero_Name = heroName;
        this.real_Name = realName;
        this.creation_year = creation_year;
    }


    public Superhero(String hero_name, String real_name) {
        this.hero_Name = hero_name;
        this.real_Name = real_name;
    }

    public Superhero(String heroName) {
        this.hero_Name = heroName;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public String getCreation_year() {
        return creation_year;
    }

    public void setCreation_year(String creation_year) {
        this.creation_year = creation_year;
    }



    /*public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }*/

    @Override
    public String toString() {
        return "Hero Name: " + hero_Name + "\n" +
                "Real Name: " + real_Name + "\n" +
                "Creation year: " + creation_year;
    }

}
