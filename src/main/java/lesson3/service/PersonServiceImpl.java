package lesson3.service;

import lesson3.dao.PersonDaoJdbc;
import lesson3.dao.PersonDaoJdbcImpl;
import lesson3.model.Person;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    private static final PersonDaoJdbc personDaoJdbc = new PersonDaoJdbcImpl();

    @Override
    public void createUsersTable() {
        personDaoJdbc.createPersonTable();
    }

    @Override
    public void savePerson(String name, String lastName, Integer age, Long departmentId) {
        personDaoJdbc.savePerson(name, lastName, age, departmentId);
    }

    @Override
    public List<Person> getAllPeoples() {
        return personDaoJdbc.getAllPeoples();
    }

    @Override
    public String getDepartmentNameByPersonId(Long personId) {
        return personDaoJdbc.getDepartmentNameByPersonId(personId);
    }
}
