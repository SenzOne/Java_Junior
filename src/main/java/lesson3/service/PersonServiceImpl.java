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
    public void savePerson(String name, String lastName, byte age) {
        personDaoJdbc.savePerson(name, lastName, age);
    }

    @Override
    public List<Person> getAllPeoples() {
        return personDaoJdbc.getAllPeoples();
    }
}
