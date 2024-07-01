package lesson3.service;

import lesson3.dao.DepartmentDaoJdbc;
import lesson3.dao.DepartmentDaoJdbcImpl;
import lesson3.model.Department;

public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentDaoJdbc departmentDaoJdbc = new DepartmentDaoJdbcImpl();

    @Override
    public void createDepartmentTable() {
        departmentDaoJdbc.getAllDepartments();
    }

    @Override
    public void saveDepartment(Department department) {
        departmentDaoJdbc.saveDepartment(department);
    }
}
