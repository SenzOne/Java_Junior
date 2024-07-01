package lesson2.anno;

import lesson2.anno.lib.ObjectCreator;
import lesson2.anno.lib.Random;

public class AnnotationsMain {

    public static void main(String[] args) {


        Person rndPerson = ObjectCreator.createObj(Person.class);
        System.out.println("age1 = " + rndPerson.age1);
        System.out.println("age2 = " + rndPerson.age2);

    }

    public static class Person {

        @Random(min = 0, max = 100)
        private int age1;

        @Random(min = 50, max = 51)
        private int age2;
    }
}
