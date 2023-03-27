package com.example.superherov5.Service;

import com.example.superherov5.DTO.CitiesDTO;
import com.example.superherov5.DTO.SuperpowerDTO;
import com.example.superherov5.Model.Superhero;
import com.example.superherov5.Repository.SuperheroRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SuperheroService {
    private SuperheroRepository superheroRepository;

    public SuperheroService(SuperheroRepository superheroRepository) {
        this.superheroRepository = superheroRepository;
    }

    public List<Superhero> getAllSuperheroes() {
        return superheroRepository.getAllSuperheroes();
    }

    public SuperpowerDTO getSuperpowersForSuperhero(String name) {
        return superheroRepository.getSuperPowersForSuperhero(name);
    }

    public void updateSuperhero(Superhero superhero){
        superheroRepository.updateSuperhero(superhero);
    }

    public List<String> getAllCities() {
        return superheroRepository.getAllCities();
    }
    public List<String> getAllSuperpowers() {
        return superheroRepository.getAllSuperpowers();
    }

    public Superhero getSuperheroById(int id){
        return superheroRepository.getSuperheroById(id);
    }

    public void createSuperhero(Superhero superhero) {
        List<String> superpowers = new ArrayList<>(superhero.getSuperpowers());
        superhero.setSuperpowers(superpowers);
        superheroRepository.createSuperhero(superhero);
    }



}
