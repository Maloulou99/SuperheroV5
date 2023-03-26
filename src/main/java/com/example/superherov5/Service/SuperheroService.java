package com.example.superherov5.Service;

import com.example.superherov5.DTO.SuperpowerDTO;
import com.example.superherov5.Model.Superhero;
import com.example.superherov5.Repository.SuperheroRepository;
import org.springframework.stereotype.Service;

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


    public void createSuperhero(Superhero superhero){
        superheroRepository.createSuperhero(superhero);
    }

    public void updateSuperhero(Superhero superhero){
        superheroRepository.updateSuperhero(superhero);
    }

    public List<String> getCities(){
        return superheroRepository.getCities();
    }

    public List<String> getPowers(){
        return superheroRepository.getPowers();
    }

}
