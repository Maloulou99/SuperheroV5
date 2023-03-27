package com.example.superherov5.Controller;

import com.example.superherov5.DTO.SuperpowerDTO;
import com.example.superherov5.Model.Superhero;
import com.example.superherov5.Service.SuperheroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/super")
public class SuperheroController {
    private SuperheroService service;

    public SuperheroController(SuperheroService service) {
        this.service = service;
    }

    @GetMapping()
    public String getAllSuperheroes(Model model) {
        List<Superhero> superheroList = service.getAllSuperheroes();
        // Liste til at fjerne duplikerede værdier, når man opdaterer siden.
        Set<Superhero> uniqueSuperheroes = new HashSet<>(superheroList);
        List<Superhero> uniqueSuperheroList = new ArrayList<>(uniqueSuperheroes);

        model.addAttribute("getAllHeroes", uniqueSuperheroList);
        return "superheores";
    }

    @GetMapping("/powers")
    public String getSuperheroPowers(@RequestParam String name, Model model) {
        SuperpowerDTO superpowers = service.getSuperpowersForSuperhero(name);
        model.addAttribute("superpowers", superpowers);
        return "superpower";
    }

    @GetMapping("/add")
    public String addSuperhero(Model model) {
        Superhero superhero = new Superhero();
        model.addAttribute("superheroForm", superhero);

        List<String> cities = service.getAllCities();
        model.addAttribute("cities", cities);

        List<String> superpowers = service.getAllSuperpowers();
        model.addAttribute("superpowers", superpowers);

        return "addsuperhero";
    }

    @PostMapping("/add")
    public String createSuperhero(@ModelAttribute Superhero superhero){
        service.createSuperhero(superhero);
        return "superheroadded";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Superhero superhero = service.getSuperheroById(id);
        model.addAttribute("superheroForm", superhero);
        model.addAttribute("cities", service.getAllCities());
        model.addAttribute("superpowers", service.getAllSuperpowers());
        model.addAttribute("currentCity", superhero.getCities().getCityName()); // Tilføjer information om nuværende by
        model.addAttribute("currentPowers", superhero.getSuperpowers()); // Tilføjer information om nuværende superkræfter
        return "updatesuper";
    }


    @PostMapping("/update/{id}")
    public String updateSuperhero(@PathVariable("id") int id, @ModelAttribute("superheroForm") Superhero superhero, Model model) {
        superhero.setId(id);
        service.updateSuperhero(superhero);
        model.addAttribute("message", "Superhelt opdateret med succes!");
        return "redirect:/superheroes";
    }


}



