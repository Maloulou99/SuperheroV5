package com.example.superherov5.Controller;

import com.example.superherov5.DTO.SuperpowerDTO;
import com.example.superherov5.Model.Superhero;
import com.example.superherov5.Service.SuperheroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        model.addAttribute("getAllHeroes", superheroList);
        return "model";
    }

    @GetMapping("/powers")
    public String getSuperheroPowers(@RequestParam String name, Model model) {
        SuperpowerDTO superpowers = service.getSuperpowersForSuperhero(name);
        model.addAttribute("superpowers", superpowers);
        return "superpower";
    }

    //Lav et nyt endpoint, /add, til oprettelse af en superhelt.
    // Der skal både laves en getmapping, der returnerer form til oprettelse, og en
    // postmapping der tilføjer data fra formens modelattribut til backenden.

    @PostMapping("/create")
    public String createProduct(@RequestParam("name") String navn,
                                @RequestParam("heroid") int herid,
                                @RequestParam("creationy") String creationy)
    {
        Superhero newProduct = new Superhero();
        newProduct.setId(herid);
        newProduct.setHero_Name(navn);
        newProduct.setCreation_year(creationy);

        service.addSuperhero(newProduct);
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("making", service.getAllSuperheroes());
        return "addsuperhero";
    }


}

