package lesson3.service;

import lesson3.model.Person;

import java.util.List;

public interface PersonService {

    void createUsersTable();

    void savePerson(String name, String lastName, Integer age, Long departmentId);

    List<Person> getAllPeoples();

    String getDepartmentNameByPersonId(Long personId);
}
