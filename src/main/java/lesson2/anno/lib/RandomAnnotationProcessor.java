package lesson2.anno.lib;

import java.lang.reflect.Field;
import java.util.Random;

public class RandomAnnotationProcessor {

    public static void processAnnotation(Object object) {

        Random random = new Random();
        Class<?> objClass = object.getClass();
        for (Field field : objClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(lesson2.anno.lib.Random.class)) {
                lesson2.anno.lib.Random annotation = field.getAnnotation(lesson2.anno.lib.Random.class);
                int max = annotation.max();
                int min = annotation.min();
                try {
                    field.setAccessible(true); // можно изменять final поля
                    field.set(object, random.nextInt(max + 1 - min) + min);
                } catch (IllegalAccessException e) {
                    System.err.println("Не удалось вставить значение в поле: " + e.getMessage());
                }
            }
        }
    }
}
