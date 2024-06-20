package lesson1.hw;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        List<Homework.Person> people = Util.setPersonData();

        people.forEach(System.out::println);

        Optional<Homework.Person> youngestPerson = Homework.findMostYoungestPerson(people);
        youngestPerson.ifPresent(person -> System.out.println("Самый молодой сотрудник: " + person));


    }
}
