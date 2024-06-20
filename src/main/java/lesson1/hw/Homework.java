package lesson1.hw;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Homework {
    static class Department {
        private String name;

        private Department(Builder builder) {
            this.name = builder.name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Department{" +
                   "name='" + name + '\'' +
                   '}';
        }

        public static class Builder {
            private String name;

            public Builder setName(String name) {
                this.name = name;
                return this;
            }

            public Department build() {
                return new Department(this);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Department that = (Department) o;
            return Objects.equals(getName(), that.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getName());
        }
    }

    static class Person {
        private String name;
        private int age;
        private double salary;
        private Department depart;

        private Person(Builder builder) {
            this.name = builder.name;
            this.age = builder.age;
            this.salary = builder.salary;
            this.depart = builder.depart;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public Department getDepart() {
            return depart;
        }

        public void setDepart(Department depart) {
            this.depart = depart;
        }

        @Override
        public String toString() {
            return "Person{" +
                   "name='" + name + '\'' +
                   ", age=" + age +
                   ", salary=" + salary +
                   ", depart=" + depart +
                   '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return getAge() == person.getAge() && Double.compare(getSalary(), person.getSalary()) == 0 && Objects.equals(getName(), person.getName()) && Objects.equals(getDepart(), person.getDepart());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getName(), getAge(), getSalary(), getDepart());
        }

        public static class Builder {
            private String name;
            private int age;
            private double salary;
            private Department depart;

            public Builder setName(String name) {
                this.name = name;
                return this;
            }

            public Builder setAge(int age) {
                this.age = age;
                return this;
            }

            public Builder setSalary(double salary) {
                this.salary = salary;
                return this;
            }

            public Builder setDepart(Department depart) {
                this.depart = depart;
                return this;
            }

            public Person build() {
                return new Person(this);
            }
        }
    }


    /**
     * Найти самого молодого сотрудника
     */
    public static Optional<Person> findMostYoungestPerson(List<Person> people) {
        return people.stream()
                .min(Comparator.comparingInt(Person::getAge));

    }

    /**
     * Найти департамент, в котором работает сотрудник с самой большой зарплатой
     */
    static Optional<Department> findMostExpensiveDepartment(List<Person> people) {
        return people.stream()
                .max(Comparator.comparingDouble(Person::getSalary))
                .map(Person::getDepart);
    }

    /**
     * Сгруппировать сотрудников по департаментам
     */
    static Map<Department, List<Person>> groupByDepartment(List<Person> people) {
        return people.stream()
                .collect(Collectors.groupingBy(Person::getDepart));
    }
//
//    /**
//     * Сгруппировать сотрудников по названиям департаментов
//     */
//    static Map<String, List<Person>> groupByDepartmentName(List<Person> people) {
//// FIXME: ваша реализация здесь
//    }
//

    /**
     * В каждом департаменте найти самого старшего сотрудника
     */
    static Map<String, Person> getDepartmentOldestPerson(List<Person> people) {
        return people.stream()
                .collect(Collectors.toMap(
                        p -> p.getDepart().getName(),
                        p -> p,
                        ((p1, p2) -> p1.getAge() > p2.getAge() ? p1 : p2
                        ))
                );
    }

    static Map<String, Person> getDepartmentOldestPersonV2(List<Person> people) {
        return people.stream()
                .collect(Collectors.toMap(person -> person.getDepart().getName(),
                        Function.identity(),
                        BinaryOperator.maxBy(Comparator.comparingInt(Person::getAge))));
    }

    static Map<String, Person> getDepartmentOldestPersonV3(List<Person> people) {
        return people.stream()
                .collect(Collectors.groupingBy(person -> person.getDepart().getName(),
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Person::getAge)),
                                Optional::get)));
    }

    /**
     * *Найти сотрудников с минимальными зарплатами в своем отделе
     * (прим. можно реализовать в два запроса)
     */
    static List<Person> cheapPersonsInDepartment(List<Person> people) {
        return new ArrayList<>(people.stream()
                .collect(Collectors.toMap(p -> p.getDepart().getName(),
                        Function.identity(),
                        BinaryOperator.minBy(Comparator.comparingDouble(Person::getSalary))))
                .values());
    }
}
