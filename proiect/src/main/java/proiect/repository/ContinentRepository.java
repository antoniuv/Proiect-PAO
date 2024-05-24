package proiect.repository;

import proiect.config.DatabaseConfiguration;
import proiect.domain.Continent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContinentRepository {
    public void insert(String name) {
        String insertContinentSql = "INSERT INTO continent (name) VALUES (?)";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(insertContinentSql);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Continent[] getAllContinents() {
        String selectAllContinentsSql = "SELECT name FROM continent";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();
        Continent[] continents = new Continent[7];

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectAllContinentsSql);
            ResultSet resultSet = preparedStatement.executeQuery();

            int i = 0;
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Continent continent = new Continent(name);
                continents[i] = continent;
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return continents;
    }

    public int getContinentId(String name) {
        String selectContinentIdSql = "SELECT id FROM continent WHERE name = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();
        int continentId = 0;

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectContinentIdSql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                continentId = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return continentId;
    }
}
