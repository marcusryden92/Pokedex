package se.marcus.pokedex.config;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

        private static final Dotenv dotenv =   Dotenv.load();

        private static final String URL = "jdbc:postgresql://localhost:5433/" + dotenv.get("POKEDEX_DB");
        private static final String USER = dotenv.get("POKEDEX_USER");
        private static final String PASSWORD = dotenv.get("POKEDEX_PASSWORD");

        public static Connection getConnection() throws SQLException {

            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
}
