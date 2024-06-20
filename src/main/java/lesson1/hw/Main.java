package lesson1.hw;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        List<Homework.Person> people = Util.setPersonData();

        people.forEach(System.out::println);

        Optional<Homework.Person> youngestPerson = Homework.findMostYoungestPerson(people);
        youngestPerson.ifPresent(person -> System.out.println("Самый молодой сотрудник: " + person));

        Optional<Homework.Department> mostExpensiveDepartment = Homework.findMostExpensiveDepartment(people);
        mostExpensiveDepartment.ifPresent(department -> System.out.println(
                "Департамент с самой большой зарплатой: " + department.getName()));


        Map<Homework.Department, List<Homework.Person>> peopleByDepartment = Homework.groupByDepartment(people);
        peopleByDepartment.forEach((department, persons) -> {
            System.out.println("Department: " + department.getName());
            persons.forEach(person -> System.out.println("  - " + person.getName()));
        });

        Map<String, Homework.Person> oldestInDepartment = Homework.getDepartmentOldestPerson(people);
        oldestInDepartment.forEach((deptName, person) -> {
            System.out.println("Самый старший сотрудник в департаменте " + deptName + ": " + person.getName() + " " + person.getAge());
        });


        List<Homework.Person> cheapPersons = Homework.cheapPersonsInDepartment(people);
        System.out.println("Сотрудники с минимальными зарплатами в своих отделах:");
        cheapPersons.forEach(System.out::println);

    }
}
