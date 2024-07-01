package lesson3.dao;

import lesson3.model.Department;
import lesson3.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJdbcImpl implements DepartmentDaoJdbc {
    private final static String CREATE_DEPARTMENT_TABLE_QUERY =
            "CREATE TABLE IF NOT EXISTS departments (id BIGINT PRIMARY KEY, name VARCHAR(128) NOT NULL)";
    private final static String INSERT_INTO_DEPARTMENT_QUERY =
            "INSERT INTO departments (id, name) VALUES (?, ?)";
    private final static String GET_ALL_DEPARTMENTS_QUERY = "SELECT * FROM departments;";
    private final static String GET_DEPARTMENT_NAME_BY_PERSON_ID_QUERY =
            "SELECT d.name FROM peoples p " +
            "JOIN departments d ON p.department_id = d.id " +
            "WHERE p.id = ?";

    private final Connection connection;

    public DepartmentDaoJdbcImpl() {
        connection = Util.getConnectionFromPool();
    }

    @Override
    public void createDepartmentTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_DEPARTMENT_TABLE_QUERY);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void saveDepartment(Department department) {
        try (var connection = Util.getConnectionFromPool();
             var preparedStatement = connection.prepareStatement(INSERT_INTO_DEPARTMENT_QUERY)) {
            preparedStatement.setLong(1, department.getId());
            preparedStatement.setString(2, department.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        try (var connection = Util.getConnectionFromPool();
             Statement statement = connection.createStatement()) {
            var executeResult = statement.executeQuery(GET_ALL_DEPARTMENTS_QUERY);
            while (executeResult.next()) {
                Department department = new Department();
                department.setId(executeResult.getLong("id"));
                department.setName(executeResult.getString("name"));

                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }


    public String getDepartmentNameByPersonId(Long personId) {
        String departmentName = null;
        try (var connection = Util.getConnectionFromPool();
             var preparedStatement = connection.prepareStatement(GET_DEPARTMENT_NAME_BY_PERSON_ID_QUERY)) {
            preparedStatement.setLong(1, personId);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                departmentName = resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentName;
    }
}

