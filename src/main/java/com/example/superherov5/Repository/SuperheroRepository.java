package com.example.superherov5.Repository;

import com.example.superherov5.DTO.SuperpowerDTO;
import com.example.superherov5.Model.Superhero;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class SuperheroRepository {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String uid;
    @Value("${spring.datasource.password}")
    private String pwd;
    private List<Superhero> superheroes = new ArrayList<>();

    public List<String> getAllCities() {
        List<String> cities = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, uid, pwd)) {
            String SQL = "SELECT * FROM city";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                String city = rs.getString("city");
                cities.add(city);
            }
            return cities;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getAllSuperpowers() {
        List<String> superpowers = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, uid, pwd)) {
            String SQL = "SELECT * FROM superpower";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                String superpower = rs.getString("superpower");
                superpowers.add(superpower);
            }
            return superpowers;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Superhero> getAllSuperheroes() {
        List<Superhero> superheroes = new ArrayList<>();
        Set<Superhero> uniqueSuperheroes = new HashSet<>(); //Ekstra liste til at fjerne duplikerede superheroes
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
                if (uniqueSuperheroes.add(superhero)) {
                    superheroes.add(superhero);
                }
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


    public void updateSuperhero(Superhero superhero) {
        try (Connection con = DriverManager.getConnection(url, uid, pwd)) {
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE superhero SET hero_name = ?, real_name = ?, creation_year = ?, city_id = (SELECT city_id FROM city WHERE city = ?) WHERE id = ?");
            stmt.setString(1, superhero.getHero_name());
            stmt.setString(2, superhero.getReal_name());
            stmt.setString(3, superhero.getCreation_year());
            stmt.setString(4, superhero.getCities().getCityName());
            stmt.setInt(5, superhero.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void createSuperhero(Superhero superhero) {
        int hero_id = 0;
        int city_id = 0;
        List<Integer> powerIds = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, uid, pwd)) {
            PreparedStatement stmt1 = con.prepareStatement("SELECT city_id FROM city WHERE city = ?");
            stmt1.setString(1, superhero.getCities().getCityName());
            ResultSet rs1 = stmt1.executeQuery();

            if (rs1.next()) {
                city_id = rs1.getInt("city_id");
            } else {
                // City doesn't exist in database, insert it and get its generated ID
                PreparedStatement stmt2 = con.prepareStatement("INSERT INTO city(city) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
                stmt2.setString(1, superhero.getCities().getCityName());
                stmt2.executeUpdate();
                ResultSet rs2 = stmt2.getGeneratedKeys();
                if (rs2.next()) {
                    city_id = rs2.getInt(1);
                }
            }

            PreparedStatement stmt2 = con.prepareStatement("INSERT INTO superhero(hero_name, real_name, creation_year, city_id) VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt2.setString(1, superhero.getHero_name());
            stmt2.setString(2, superhero.getReal_name());
            stmt2.setString(3, superhero.getCreation_year());
            stmt2.setInt(4, city_id);
            stmt2.executeUpdate();
            ResultSet rs3 = stmt2.getGeneratedKeys();
            if (rs3.next()) {
                hero_id = rs3.getInt(1);
                superhero.setId(hero_id);
            }

            List<String> superpowers = superhero.getSuperpowers();
            for (String power : superpowers) {
                PreparedStatement stmt3 = con.prepareStatement("SELECT superpower_id FROM superpower WHERE superpower = ?");
                stmt3.setString(1, power);
                ResultSet rs4 = stmt3.executeQuery();
                if (rs4.next()) {
                    // Superpower already exists in database, get its ID
                    powerIds.add(rs4.getInt("superpower_id"));
                } else {
                    // Superpower doesn't exist in database, insert it and get its generated ID
                    PreparedStatement stmt5 = con.prepareStatement("INSERT INTO superpower(superpower) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
                    stmt5.setString(1, power);
                    stmt5.executeUpdate();
                    ResultSet rs5 = stmt5.getGeneratedKeys();
                    if (rs5.next()) {
                        powerIds.add(rs5.getInt(1));
                    }
                }
            }

            PreparedStatement stmt4 = con.prepareStatement("INSERT INTO superhero_superpower(superhero_id, superpower_id) VALUES (?, ?)");
            for (Integer powerId : powerIds) {
                stmt4.setInt(1, hero_id);
                stmt4.setInt(2, powerId);
                stmt4.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Superhero getSuperheroById(int id) {
        try (Connection con = DriverManager.getConnection(url, uid, pwd)) {
            String SQL = "SELECT * FROM superhero WHERE superhero_id = ?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Superhero superhero = new Superhero(
                        rs.getInt("superhero_id"),
                        rs.getString("hero_name"),
                        rs.getString("real_name"),
                        rs.getString("creation_year"),
                        rs.getInt("city_id")
                );
                return superhero;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}



