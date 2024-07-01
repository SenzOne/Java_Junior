package lesson3.dao;

import lesson3.model.Person;

import java.util.List;

public interface PersonDaoJdbc {

    void createPersonTable();

    void savePerson(String name, String lastName, byte age);

    List<Person> getAllPeoples();

}
