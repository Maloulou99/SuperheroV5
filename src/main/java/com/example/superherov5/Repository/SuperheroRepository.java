package com.example.superherov5.Repository;

import com.example.superherov5.DTO.SuperpowerDTO;
import com.example.superherov5.Model.Superhero;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
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


    public void deleteSuperhero(int id) {
        for (int i = 0; i < superheroes.size(); i++) {
            if (superheroes.get(i).getId() == id) {
                superheroes.remove(i);
                break;
            }
        }
    }

    public void updateSuperhero(Superhero superhero) {
        try (Connection con = DriverManager.getConnection(url, uid, pwd)) {
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE superheroes SET hero_name=?, real_name=?, creation_year=?, superpowers=?, city=?, WHERE id=?");
            stmt.setString(1, superhero.getHero_Name());
            stmt.setString(2, superhero.getReal_Name());
            stmt.setString(3, superhero.getCreation_year());
            stmt.setString(4, superhero.getSuperpower());
            stmt.setString(5, superhero.getCitiesDTO().getCityName());
            stmt.setInt(6, superhero.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void createSuperhero(Superhero superhero) {
        List<Integer> powerList = new ArrayList<>();
        int hero_id = 0;
        int city_id = 0;
        try (Connection con = DriverManager.getConnection(url, uid, pwd)) {

            PreparedStatement stmt4 = con.prepareStatement("SELECT city_id FROM city WHERE city = ?");
            stmt4.setString(1, superhero.getCitiesDTO().getCityName());
            ResultSet rs = stmt4.executeQuery();
            if (rs.next()) {
                city_id = rs.getInt("city_id");
            }

            PreparedStatement stmt5 = con.prepareStatement("SELECT superpower_id FROM superpower WHERE superpower = ?");
            stmt5.setString(1, superhero.getSuperpowerDTO().getName());
            ResultSet rs1 = stmt5.executeQuery();
            for (String power : superhero.getSuperpowerDTO().getSuperPowers())
                stmt5.setString(1, power);
            if(rs.next()){
                powerList.add(rs1.getInt("superpower_id"));
            }

            PreparedStatement stmt6 = con.prepareStatement("SELECT hero_id FROM superhero_superpower WHERE hero_id = ?");
            stmt6.setInt(1, superhero.getId());
            ResultSet rs2 = stmt6.executeQuery();
            if (rs.next()){
                hero_id = rs2.getInt("hero_id");
            }

            PreparedStatement stmt1 = con.prepareStatement("INSERT INTO superhero(hero_name, real_name, creation_year, city_id) VALUES (?, ?, ?, ?)");
            stmt1.setString(1, superhero.getHero_Name());
            stmt1.setString(2, superhero.getReal_Name());
            stmt1.setString(3, superhero.getCreation_year());
            stmt1.setInt(4, city_id);
            stmt1.executeUpdate();

            PreparedStatement stmt2 = con.prepareStatement("INSERT INTO superpower(superpower) VALUES (?, ?)");
            for (int i = 0; i < powerList.size(); i++) {
                stmt2.setInt(1, powerList.get(i));
                stmt2.setInt(2, hero_id);
                stmt2.executeUpdate();
            }

            PreparedStatement stmt3 = con.prepareStatement("INSERT INTO city(city) VALUES (?)");
            stmt3.setString(1, superhero.getCitiesDTO().getCityName());
            stmt3.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}



