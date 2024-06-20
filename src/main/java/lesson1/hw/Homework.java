package lesson1.hw;

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


//    /**
//     * Найти самого молодого сотрудника
//     */
//    static Optional<Person> findMostYoungestPerson(List<Person> people) {
//// FIXME: ваша реализация здесь
//
//    }
//
//    /**
//     * Найти департамент, в котором работает сотрудник с самой большой зарплатой
//     */
//    static Optional<Department> findMostExpensiveDepartment(List<Person> people) {
//// FIXME: ваша реализация здесь
//    }
//
//    /**
//     * Сгруппировать сотрудников по департаментам
//     */
//    static Map<Department, List<Person>> groupByDepartment(List<Person> people) {
//// FIXME: ваша реализация здесь
//    }
//
//    /**
//     * Сгруппировать сотрудников по названиям департаментов
//     */
//    static Map<String, List<Person>> groupByDepartmentName(List<Person> people) {
//// FIXME: ваша реализация здесь
//    }
//
//    /**
//     * В каждом департаменте найти самого старшего сотрудника
//     */
//    static Map<String, Person> getDepartmentOldestPerson(List<Person> people) {
//// FIXME: ваша реализация здесь
//    }
//
//    /**
//     * *Найти сотрудников с минимальными зарплатами в своем отделе
//     * (прим. можно реализовать в два запроса)
//     */
//    static List<Person> cheapPersonsInDepartment(List<Person> people) {
//// FIXME: ваша реализация здесь
//    }
}
