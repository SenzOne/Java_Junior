package lesson3.dao;

import lesson3.model.Person;
import lesson3.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoJdbcImpl implements PersonDaoJdbc {
    private final static String CREATE_DEPARTMENT_TABLE_QUERY =
            "CREATE TABLE IF NOT EXISTS departments (id BIGINT PRIMARY KEY, name VARCHAR(128) NOT NULL)";
    private final static String CREATE_PERSON_TABLE_QUERY =
            "CREATE TABLE IF NOT EXISTS peoples (id BIGINT PRIMARY KEY, name VARCHAR(25), lastName VARCHAR(25), age INTEGER, department_id BIGINT, FOREIGN KEY (department_id) REFERENCES departments(id))";
    private final static String INSERT_INTO_PERSON_QUERY =
            "INSERT INTO peoples (id, name, lastName, age, department_id) VALUES (nextval('peoples_id_seq'),?,?,?,?)";
    private final static String GET_ALL_USERS_QUERY = "SELECT * FROM peoples;";
    private final static String GET_DEPARTMENT_NAME_BY_PERSON_ID_QUERY =
            "SELECT d.name FROM peoples p JOIN departments d ON p.department_id = d.id WHERE p.id = ?";

    private final Connection connection;

    public PersonDaoJdbcImpl() {
        connection = Util.getConnectionFromPool();
    }

    @Override
    public void createPersonTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_DEPARTMENT_TABLE_QUERY);
            statement.execute(CREATE_PERSON_TABLE_QUERY);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void savePerson(String name, String lastName, Integer age, Long departmentId) {
        try (var connection = Util.getConnectionFromPool();
             var preparedStatement = connection.prepareStatement(INSERT_INTO_PERSON_QUERY)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.setLong(4, departmentId);
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
                person.setDepartmentId(executeResult.getObject("department_id", Long.class));

                userFromDb.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userFromDb;
    }

    @Override
    public String getDepartmentNameByPersonId(Long personId) {
        try (var connection = Util.getConnectionFromPool();
             var preparedStatement = connection.prepareStatement(GET_DEPARTMENT_NAME_BY_PERSON_ID_QUERY)) {
            preparedStatement.setLong(1, personId);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}