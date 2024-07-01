package lesson3.dao;

import lesson3.model.Person;
import lesson3.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoJdbcImpl implements PersonDaoJdbc {

    private final static String CREATE_USERS_QUERY =
            "CREATE TABLE IF NOT EXISTS peoples (id BIGINT PRIMARY KEY, name VARCHAR(25), lastName VARCHAR(25), age integer)";
    private final static String INSERT_INTO_PERSON_QUERY =
            "INSERT INTO peoples (id, name, lastName, age) VALUES (nextval('peoples_id_seq'),?,?,?)";
    private final static String GET_ALL_USERS_QUERY = "SELECT * FROM peoples;";

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
             var preparedStatement = connection.prepareStatement(INSERT_INTO_PERSON_QUERY)) {
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
        List<Person> userFromDb = new ArrayList<>();
        try (var connection = Util.getConnectionFromPool();
             Statement statement = connection.createStatement()) {
            var executeResult = statement.executeQuery(GET_ALL_USERS_QUERY);
            while (executeResult.next()) {
                Person person = new Person();
                person.setId((long) executeResult.getInt("id"));
                person.setName(executeResult.getObject("name", String.class));
                person.setLastName(executeResult.getObject("lastname", String.class));
                person.setAge(executeResult.getObject("age", Integer.class));

                userFromDb.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userFromDb;
    }
}
