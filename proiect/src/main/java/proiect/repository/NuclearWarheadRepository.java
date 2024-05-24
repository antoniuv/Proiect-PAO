package proiect.repository;

import proiect.config.DatabaseConfiguration;
import proiect.domain.Bomb;
import proiect.domain.Country;
import proiect.domain.NuclearWarhead;
import proiect.domain.President;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class NuclearWarheadRepository {

    public void insert(int country_id, int range, double speed, String location) {
        String insertNukeSql = "INSERT INTO nuclearWarhead (country_id, rangecruise, speed, location) VALUES (?, ?, ?, ?)";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(insertNukeSql);
            preparedStatement.setInt(1, country_id);
            preparedStatement.setInt(2, range);
            preparedStatement.setDouble(3, speed);
            preparedStatement.setString(4, location);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public NuclearWarhead[] getNukes(int country_id) {
        NuclearWarhead[] nukes = new NuclearWarhead[0];
        String selectCountrySql = "SELECT * FROM nuclearWarhead WHERE country_id = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectCountrySql);
            preparedStatement.setInt(1, country_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int nuke_id = resultSet.getInt("id");
                int co_id = resultSet.getInt("country_id");
                int range = resultSet.getInt("rangecruise");
                double speed = resultSet.getDouble("speed");
                String location = resultSet.getString("location");


                NuclearWarhead nuke = new NuclearWarhead(range, speed, location);
                nuke.setId(nuke_id);

                BombRepository bombRepository = new BombRepository();
                Bomb bomb = bombRepository.getBomb(nuke_id);
                nuke.attachPayload(bomb);

                nukes = Arrays.copyOf(nukes, nukes.length + 1);
                nukes[nukes.length - 1] = nuke;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nukes;
    }


    public int getIdOfMostRecentNuke() {
        int nuke_id = 0;
        String selectNukeIdSql = "SELECT max(id) FROM nuclearWarhead";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectNukeIdSql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                nuke_id = resultSet.getInt("max(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nuke_id;
    }
}
