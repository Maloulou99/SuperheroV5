package com.example.superherov5.Repository;

import com.example.superherov5.DTO.SuperpowerDTO;
import com.example.superherov5.Model.Superhero;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SuperheroRepository {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String uid;
    @Value("${spring.datasource.password}")
    private String pwd;
    private List<Superhero> superheroes = new ArrayList<>();

    // En liste med alle superhelte, der indeholder: heroName, realName og creationYear
    public List<Superhero> getAllSuperheroes() {
        try (Connection con = DriverManager.getConnection(url, uid, pwd)) {
            String SQL = "SELECT * FROM superhero";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                Superhero superhero = new Superhero(
                        rs.getString("hero_name"),
                        rs.getString("real_name"),
                        rs.getString("creation_year")
                );
                superheroes.add(superhero);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return superheroes;
    }

    public SuperpowerDTO getSuperPowersForSuperhero(String name) {
        List<String> superheroes = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, uid, pwd)) {
            String SQL = "SELECT superpower " +
                    "FROM superhero " +
                    "LEFT JOIN superhero_superpower ON superhero.superhero_id = superhero_superpower.hero_id " +
                    "LEFT JOIN superpower ON superhero_superpower.superpower_id = superpower.superpower_id " +
                    "where superhero.hero_name = ?";
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                superheroes.add(rs.getString("superpower"));
            }
            System.out.println(superheroes);
            return new SuperpowerDTO(name, superheroes);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Superhero getSuperheroById(int id) {
        for (Superhero superhero : superheroes) {
            if (superhero.getId() == id) {
                return superhero;
            }
        }
        return null;
    }


    public void addSuperhero(Superhero superhero) {
        for (int i = 0; i < superheroes.size(); i++) {
            superheroes.add(0, superhero);
        }
    }


    public void updateSuperhero(Superhero superhero) {
        for (int i = 0; i < superheroes.size(); i++) {
            if (superheroes.get(i).getId() == superhero.getId()) {
                superheroes.set(i, superhero);
                break;
            }
        }
    }

    public void deleteSuperhero(int id) {
        for (int i = 0; i < superheroes.size(); i++) {
            if (superheroes.get(i).getId() == id) {
                superheroes.remove(i);
                break;
            }
        }
    }

}



