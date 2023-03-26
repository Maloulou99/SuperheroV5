package com.example.superherov5.Controller;

import com.example.superherov5.DTO.CitiesDTO;
import com.example.superherov5.DTO.SuperheroFormDTO;
import com.example.superherov5.DTO.SuperpowerDTO;
import com.example.superherov5.Model.Superhero;
import com.example.superherov5.Service.SuperheroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        Superhero superhero = new Superhero();
        model.addAttribute("superhero", superhero);

        List<String> cityList = service.getCities();
        model.addAttribute("cityList", cityList);

        List<String> powerList = service.getPowers();
        model.addAttribute("powerList", powerList);
        return "addsuperhero";
    }

    @GetMapping("/added")
    public String showSuperheroAdded() {
        return "superheroadded";
    }

    @PostMapping("/add")
    public String createSuperhero(@ModelAttribute Superhero superhero){
        service.createSuperhero(superhero);
        return "superheroadded";
    }
    @GetMapping("/update")
    public String showUpdateForm(Model model) {
        Superhero superhero = service.getAllSuperheroes().get(0);
        SuperheroFormDTO superheroForm = new SuperheroFormDTO(superhero);
        model.addAttribute("superheroForm", superheroForm);
        model.addAttribute("cities", service.getCities());
        model.addAttribute("superpowers", service.getPowers());
        return "updatesuper";
    }



    @PostMapping("/update/{id}")
    public String updateSuperhero(@PathVariable("id") int id, @ModelAttribute("superhero") Superhero superhero, Model model) {
        superhero.setId(id);
        service.updateSuperhero(superhero);
        model.addAttribute("message", "Superhelt opdateret med succes!");
        return "redirect:/superheroes";
    }


}



