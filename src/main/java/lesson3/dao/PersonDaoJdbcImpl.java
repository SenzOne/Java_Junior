package lesson3.dao;

import lesson3.model.Person;
import lesson3.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PersonDaoJdbcImpl implements PersonDaoJdbc {

    private final static String CREATE_USERS_QUERY =
            "CREATE TABLE IF NOT EXISTS peoples (id BIGINT PRIMARY KEY, name VARCHAR(25), lastName VARCHAR(25), age SMALLINT)";
    private final static String INSERT_INTO_USERS_QUERY = "INSERT INTO peoples (id, name, lastName, age) VALUES (nextval('peoples_id_seq'),?,?,?)";

    private final Connection connection;

    public PersonDaoJdbcImpl() {
        connection = Util.getConnectionFromPool();
    }


    @Override
    public void createPersonTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_USERS_QUERY);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void savePerson(String name, String lastName, byte age) {
        try (var connection = Util.getConnectionFromPool();
             var preparedStatement = connection.prepareStatement(INSERT_INTO_USERS_QUERY)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> getAllPeoples() {
        return null;
    }
}
