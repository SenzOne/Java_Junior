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
                    new Person("Alice", "Smith", (byte) 25),
                    new Person("John", "Doe", (byte) 30),
                    new Person("Emma", "Johnson", (byte) 22),
                    new Person("Michael", "Williams", (byte) 35),
                    new Person("Sophia", "Brown", (byte) 28)
            ));

            PersonService personService = new PersonServiceImpl();
            personService.createUsersTable();
            people.forEach(x -> personService.savePerson(x.getName(), x.getLastName(), x.getAge()));
//            userService.getAllUsers().forEach(System.out::println);
        } finally {
            Util.closePool();
        }
    }



}
