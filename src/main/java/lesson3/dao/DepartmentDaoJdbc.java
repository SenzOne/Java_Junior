package lesson3.dao;

import lesson3.model.Department;

import java.util.List;

public interface DepartmentDaoJdbc {
    void createDepartmentTable();
    void saveDepartment(Department department);
    List<Department> getAllDepartments();

}
