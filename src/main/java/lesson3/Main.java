package lesson3;

import lesson3.dao.DepartmentDaoJdbcImpl;
import lesson3.model.Department;
import lesson3.model.Person;
import lesson3.service.DepartmentService;
import lesson3.service.DepartmentServiceImpl;
import lesson3.service.PersonService;
import lesson3.service.PersonServiceImpl;
import lesson3.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            List<Department> departments = new ArrayList<>(List.of(
                    new Department(4L, "HR"),
                    new Department(5L, "IT"),
                    new Department(6L, "Sales")
            ));

            List<Person> people = new ArrayList<>(List.of(
                    new Person("Alice", "Smith", 25, 1L),
                    new Person("John", "Doe", 30, 2L),
                    new Person("Emma", "Johnson", 22, 3L),
                    new Person("Michael", "Williams", 35, 1L),
                    new Person("Sophia", "Brown", 28, 2L)
            ));


            DepartmentService departmentService = new DepartmentServiceImpl();
            departmentService.createDepartmentTable();
            departments.forEach(departmentService::saveDepartment);

            PersonService personService = new PersonServiceImpl();
            personService.createUsersTable();
            people.forEach(x -> personService.savePerson(x.getName(), x.getLastName(), x.getAge(), x.getDepartmentId()));
            personService.getAllPeoples().forEach(System.out::println);
            System.out.println(personService.getDepartmentNameByPersonId(11L)); // HR


        } finally {
            Util.closePool();
        }


    }
}
