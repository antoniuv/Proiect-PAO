package proiect.repository;

import proiect.config.DatabaseConfiguration;
import proiect.domain.Bomb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BombRepository {
    public void insert(int nuke_id, String type, double yield) {
        String insertBombSql = "INSERT INTO bomb (nuclearWarhead_id, type, yield) VALUES (?, ?, ?)";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(insertBombSql);
            preparedStatement.setInt(1, nuke_id);
            preparedStatement.setString(2, type);
            preparedStatement.setDouble(3, yield);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Bomb getBomb (int nuke_id){
        Bomb bomb = null;
        String selectBombSql = "SELECT * FROM bomb where nuclearWarhead_id = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectBombSql);
            preparedStatement.setInt(1, nuke_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String type = resultSet.getString("type");
                double yield = resultSet.getDouble("yield");

                bomb = new Bomb(type, yield);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bomb;
    }
}
