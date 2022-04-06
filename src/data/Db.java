package data;

import java.util.Optional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private static Db instance;
    private static String url = "jdbc:sqlite:test.db";

    private Connection connection;

    private Db() throws SQLException {
        this.connection = DriverManager.getConnection(url);
    }

    public static Optional<Db> instance() {
        if (instance == null) {
            try {
                return Optional.of(new Db());
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
                return Optional.empty();
            }
        }

        return Optional.of(instance);
    }
}
