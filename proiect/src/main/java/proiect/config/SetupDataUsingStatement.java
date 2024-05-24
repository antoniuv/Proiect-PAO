package proiect.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class SetupDataUsingStatement {


    public void Tables() {
        String createPresidentTableSql = """
            CREATE TABLE IF NOT EXISTS president (
                id int PRIMARY KEY AUTO_INCREMENT,
                firstname varchar(40),
                lastname varchar(40),
                age int,
                party varchar(40)
            )
            """;

        String createContinentTableSql = """
            CREATE TABLE IF NOT EXISTS continent (
                id int PRIMARY KEY AUTO_INCREMENT,
                name varchar(100)
        )
        """;

        String createCountryTableSql = """
            CREATE TABLE IF NOT EXISTS country (
                id int PRIMARY KEY AUTO_INCREMENT,
                name varchar(100),
                surface int,
                president_id int,
                research_level int DEFAULT 0,
                continent_id int,
                FOREIGN KEY (president_id) REFERENCES president(id),
                FOREIGN KEY (continent_id) REFERENCES continent(id)
        )
        """;


        String createNuclearWarheadTableSql = """
            CREATE TABLE IF NOT EXISTS nuclearWarhead (
                id int PRIMARY KEY AUTO_INCREMENT,
                country_id int,
                rangecruise int,
                speed double,
                location varchar(40),
                FOREIGN KEY (country_id) REFERENCES country(id)
        )
        """;

        String createBombTableSql = """
            CREATE TABLE IF NOT EXISTS bomb (
                id int PRIMARY KEY AUTO_INCREMENT,
                nuclearWarhead_id int,
                type varchar(40),
                yield double,
                FOREIGN KEY (nuclearWarhead_id) REFERENCES nuclearWarhead(id)
        )
        """;

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();


            statement.execute(createPresidentTableSql);

            statement.execute(createContinentTableSql);

            statement.execute(createCountryTableSql);

            statement.execute(createNuclearWarheadTableSql);

            statement.execute(createBombTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllPresidents() {
        String selectSql = "SELECT * FROM president";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while(resultSet.next()) {
                System.out.println("Id: " + resultSet.getInt(1));
                System.out.println("First Name: " + resultSet.getString(2));
                System.out.println("Last Name: " + resultSet.getString(3));
                System.out.println("Age: " + resultSet.getInt(4));
                System.out.println("Party: " + resultSet.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
