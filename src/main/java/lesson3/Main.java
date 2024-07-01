package lesson3;

import lesson3.model.Person;
import lesson3.service.PersonService;
import lesson3.service.PersonServiceImpl;
import lesson3.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        PersonDaoJdbcImpl personDaoJdbc = new PersonDaoJdbcImpl();
//        personDaoJdbc.createUsersTable();



        try {
            List<Person> people = new ArrayList<>(List.of(
                    new Person("Alice", "Smith", 25),
                    new Person("John", "Doe", 30),
                    new Person("Emma", "Johnson", 22),
                    new Person("Michael", "Williams",35),
                    new Person("Sophia", "Brown", 28)
            ));

            PersonService personService = new PersonServiceImpl();
//            personService.createUsersTable();
//            people.forEach(x -> personService.savePerson(x.getName(), x.getLastName(), x.getAge()));
            personService.getAllPeoples().forEach(System.out::println);
        } finally {
            Util.closePool();
        }
    }



}
