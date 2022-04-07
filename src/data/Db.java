package data;

import java.util.Optional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import models.*;

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

    public Optional<Boolean> setup() {
        try {
            return Optional.of(connection.createStatement().execute("""
                create table if not exists Operations ( 
                    plane varchar(20),
                    kind  varchar(10),
                    date  date,
                    city  varchar(20),
                    cause text
                )"""));
        }
        catch (SQLException e) {
            return Optional.empty();
        }
    }

    public PreparedStatement createInsert(Landing landing) {
        var sql = "insert into Operations (plane, kind, city) values (?, ?, ?)";
        var statement = connection.prepareStatement(sql);
        statement.setString(1, landing.planeCode());
        statement.setString(2, "Aterrizaje");
        statement.setString(3, landing.origin());
    }

    public Optional<Boolean> add(Landing operation) {
        try {
            var statement = createInsert(operation);
            return Optional.of(statement.execute());
        }
        catch (SQLException e) {
            return Optional.empty();
        }
    }
}
