package proiect.repository;

import proiect.config.DatabaseConfiguration;
import proiect.domain.Continent;
import proiect.domain.Country;
import proiect.domain.NuclearWarhead;
import proiect.domain.President;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class CountryRepository {
    public void insert(String name, int surface, int research_level, int continent_id) {
        String insertPersonSql = "INSERT INTO country (name, surface, president_id, research_level, continent_id) VALUES (?, ?, null, ?, ?)";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, surface);
            preparedStatement.setInt(3, research_level);
            preparedStatement.setInt(4, continent_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Country[] getCountries(int continent_id){
        Country[] countries = new Country[0];
        String selectCountrySql = "SELECT * FROM country WHERE continent_id = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectCountrySql);
            preparedStatement.setInt(1, continent_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int country_id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int surface = resultSet.getInt("surface");
                int president_id = resultSet.getInt("president_id");
                int research_level = resultSet.getInt("research_level");


                Country country = new Country(name, surface);
                country.setResearchLevel(research_level);

                PresidentRepository pr = new PresidentRepository();
                President president = pr.getPresident(president_id);

                country.setPresident(president);

                NuclearWarheadRepository nuclearWarheadRepository = new NuclearWarheadRepository();
                NuclearWarhead[] nukes = nuclearWarheadRepository.getNukes(country_id);

                country.setNuclearWarheads(nukes);

                countries = Arrays.copyOf(countries, countries.length + 1);
                countries[countries.length - 1] = country;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;
    }

    public void updatePresident(String name, int president_id) {
        String updatePresidentSql = "UPDATE country SET president_id = ? WHERE name = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(updatePresidentSql);
            preparedStatement.setInt(1, president_id);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void IncreaseResearchLevel(String name) {
        String updatePresidentSql = "UPDATE country SET research_level = research_level + 1 WHERE name = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(updatePresidentSql);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int getCountryId(String name) {
        String selectPresidentSql = "SELECT id FROM country WHERE name = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();
        int countryId = 0;

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectPresidentSql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                countryId = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countryId;
    }
}
