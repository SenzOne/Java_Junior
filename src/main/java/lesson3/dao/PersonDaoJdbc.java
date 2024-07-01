package lesson3.dao;

import lesson3.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonDaoJdbc {

    private final static String CREATE_USERS_QUERY =
            "CREATE TABLE IF NOT EXISTS users (id SERIAL, name VARCHAR(25), lastName VARCHAR(25), age SMALLINT)";

    private final Connection connection;

    public PersonDaoJdbc() {
        connection = Util.getConnectionFromPool();
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_USERS_QUERY);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
