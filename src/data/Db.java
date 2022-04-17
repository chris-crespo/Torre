package data;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.time.LocalDateTime;

import java.sql.*;

import models.*;
import utils.Result;

interface RowMapper<T> {
    T apply(ResultSet rs) throws SQLException;
}

interface StatementPrep<T> {
    PreparedStatement apply(T obj, PreparedStatement statement) throws SQLException;
}

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

    private boolean createTables() throws SQLException {
       connection.createStatement().execute("""
            create table if not exists Operations ( 
                plane varchar(20),
                kind  varchar(10),
                date  text,
                city  varchar(20),
                cause text
            );""");

       return connection.createStatement().execute("""
            create table if not exists Auths (
                plane     varchar(20),
                op_date   text,
                auth_date text,
                foreign key(plane, op_date) references Operations(plane, date)
            );""");
    }

    public Result<Boolean> setup() {
        return Result.of(this::createTables);
    }

    private Authorization mapAuth(ResultSet rs) throws SQLException {
        var plane   = rs.getString(1);
        var kind    = rs.getString(2);
        var op_date = LocalDateTime.parse(rs.getString(3));
        var city    = rs.getString(4);
        var date    = LocalDateTime.parse(rs.getString(6));

        var op = switch (kind) {
            case "Aterrizaje" -> new Landing(plane, op_date, city, SpecialCause.from(rs.getString(5)));
            default           -> new TakeOff(plane, op_date, city);
        };

        return new Authorization(op, date);
    }

    private PreparedStatement prepareOpStatement(Operation operation, PreparedStatement statement) 
            throws SQLException {
        statement.setString(1, operation.planeCode());
        statement.setString(2, operation.kind());
        statement.setString(3, operation.date().toString());
        statement.setString(4, operation.city());
        statement.setString(5, operation.cause().label());

        return statement;
    }

    public PreparedStatement prepareAuthStatement(Authorization auth, PreparedStatement statement) 
            throws SQLException {
        var op = auth.operation();
        statement.setString(1, op.planeCode());
        statement.setString(2, op.date().toString());
        statement.setString(3, auth.date().toString());
        return statement;
    }

    private <T> List<T> mapRs(ResultSet rs, RowMapper<T> fn) throws SQLException {
        return new ArrayList<>() {{
            while (rs.next()) add(fn.apply(rs));
        }};
    }   

    private <T> List<T> fetch(String query, RowMapper<T> fn) throws SQLException {
        try (var rs = connection.createStatement().executeQuery(query)) {
            return mapRs(rs, fn);
        }
    }

    private <T> int insert(String query, T obj, StatementPrep<T> fn) throws SQLException {
        return fn.apply(obj, connection.prepareStatement(query)).executeUpdate();
    }

    public Result<List<Authorization>> fetchAuths() {
        var query = """
            select ops.plane, ops.kind, auths.op_date, ops.city, ops.cause, auths.auth_date
            from Operations ops join Auths auths
            on ops.plane = auths.plane and ops.date = auths.op_date
            where substr(auths.op_date, 10) = date('now')""";
        return Result.of(() -> fetch(query, this::mapAuth));
    }

    public Result<Integer> insertOp(Operation op) {
        var query = "insert into Operations values (?, ?, ?, ?, ?)";
        return Result.of(() -> insert(query, op, this::prepareOpStatement));
    }

    public Result<Integer> insertAuth(Authorization auth) {
        var query = "insert into Auths values (?, ?, ?)";
        return Result.of(() -> insert(query, auth, this::prepareAuthStatement));
    }
}
