package lesson2.hw;

import java.lang.reflect.Field;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

public class RandomDateProcessor {
    public static void processAnnotations(Object obj) throws IllegalAccessException {
        Class<?> objClass = obj.getClass();
        for (Field field : objClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(RandomDate.class)) {
                if (!field.getType().equals(Date.class)) {
                    System.err.println("Аннотация RandomDate поддерживается только для полей типа java.util.Date");
                    continue;
                }

                RandomDate annotation = field.getAnnotation(RandomDate.class);
                long min = annotation.min();
                long max = annotation.max();
                String zone = annotation.zone();

                if (min >= max) {
                    throw new IllegalArgumentException("Значение min должно быть меньше значения max");
                }

                Random random = new Random();
                long randomTimestamp = min + (long) (random.nextDouble() * (max - min));

                ZoneId zoneId;
                try {
                    zoneId = ZoneId.of(zone);
                } catch (Exception e) {
                    System.err.println("Неверный идентификатор часового пояса: " + zone);
                    zoneId = ZoneId.systemDefault(); // Использование системного часового пояса по умолчанию
                }

                Instant randomInstant = Instant.ofEpochMilli(randomTimestamp);
                Date randomDate = Date.from(randomInstant.atZone(zoneId).toInstant());

                field.setAccessible(true);
                field.set(obj, randomDate);
            }
        }
    }
}

