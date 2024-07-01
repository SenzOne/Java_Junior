package lesson2.hw;

import lesson2.anno.lib.ObjectCreator;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        TestRandomDateClass testClass = ObjectCreator2.createObj(TestRandomDateClass.class);

        if (testClass != null) {
            Date randomDate = testClass.getRandomDate();
            System.out.println("Generated Random Date: " + randomDate);

            // Преобразование обратно в дату для чтения
            Instant instant = randomDate.toInstant();
            LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            System.out.println("Generated Random Date (formatted): " + dateTime);
        } else {
            System.err.println("Не удалось создать объект класса MyClass");
        }
    }
}
