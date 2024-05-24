package proiect.repository;

import proiect.domain.Country;
import proiect.domain.Person;
import proiect.config.DatabaseConfiguration;
import proiect.domain.President;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Optional;

public class PresidentRepository {
    public void insert(String firstname, String lastname, double age, String party) {
        String insertPersonSql = "INSERT INTO president (firstname, lastname, age, party) VALUES (?, ?, ?, ?)";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setDouble(3, age);
            preparedStatement.setString(4, party);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateName(String name, int id) {
        String updatePersonSql = "UPDATE president SET name = ? WHERE id = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(updatePersonSql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePresident(int id) {
        String deletePresidentSql = "DELETE FROM president WHERE id = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(deletePresidentSql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getPresidentId(String firstname, String lastname) {
        String selectPresidentSql = "SELECT id FROM president WHERE firstname = ? and lastname = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();
        int PresidentId = 0;

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectPresidentSql);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                PresidentId = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PresidentId;
    }

    public President getPresident(int id) {
        String selectPresidentSql = "SELECT * FROM president WHERE id = ?";
        President president = null;
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectPresidentSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int president_id = resultSet.getInt("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                int age = resultSet.getInt("age");
                String party = resultSet.getString("party");


                president = new President(firstname, lastname, age, party);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return president;
    }
}
