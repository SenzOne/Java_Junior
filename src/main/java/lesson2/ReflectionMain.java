package lesson2;

public class ReflectionMain {

    private static class Person {
        private static long counter = 0L;
        private final String name;
        private int age;

        public Person() {
            this("unnamed");
        }

        public Person(String name) {
            this.name = name;
            counter++;
        }


        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public static long getCounter() {
            return counter;
        }
    }
}
