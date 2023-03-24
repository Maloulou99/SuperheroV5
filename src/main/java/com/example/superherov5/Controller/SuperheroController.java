package com.example.superherov5.Controller;

import com.example.superherov5.DTO.SuperpowerDTO;
import com.example.superherov5.Model.Superhero;
import com.example.superherov5.Service.SuperheroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/add")
    public String addSuperhero(Model model) {
        model.addAttribute("superhero", new Superhero());
        return "addsuperhero";
    }

    @PostMapping("/add")
    public String addSuperhero2(@ModelAttribute("superhero") Superhero superhero, Model model) {
        service.createSuperhero(superhero);
        model.addAttribute("message", "Superhelt tilføjet med succes!");
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateSuperhero(@ModelAttribute("superhero") Superhero superhero, Model model) {
        service.updateSuperhero(superhero);
        model.addAttribute("message", "Superhelt opdateret med succes!");
        return "redirect:.../add";
    }



}



