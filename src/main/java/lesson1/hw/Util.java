package lesson1.hw;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Util {

    private static <T> T getRandom(List<? extends T> items) {
        int randomIndex = ThreadLocalRandom.current().nextInt(0, items.size());
        return items.get(randomIndex);
    }


     static List<Homework.Person> setPersonData() {
        List<String> names = List.of("John Doe", "Jane Smith", "Alice Johnson", "Bob Brown", "Charlie Davis");
        List<String> departments = List.of("IT", "HR", "Finance", "Marketing", "Sales");

        List<Homework.Person> people = new ArrayList<>();

        for (String name : names) {
            Homework.Department department = new Homework.Department.Builder()
                    .setName(getRandom(departments))
                    .build();

            Homework.Person person = new Homework.Person.Builder()
                    .setName(name)
                    .setAge(ThreadLocalRandom.current().nextInt(20, 60))
                    .setSalary(ThreadLocalRandom.current().nextDouble(30000, 100000))
                    .setDepart(department)
                    .build();

            people.add(person);
        }

        return people;
    }
}
