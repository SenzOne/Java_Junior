package lesson2.hw;

import java.lang.reflect.Constructor;

public class ObjectCreator2 {

    public static <T> T createObj(Class<T> tClass) {
        try {
            Constructor<T> constructor = tClass.getDeclaredConstructor();
            constructor.setAccessible(true); // На случай если конструктор не публичный
            T obj = constructor.newInstance();
            RandomDateProcessor.processAnnotations(obj);
            return obj;
        } catch (Exception e) {
            System.err.println("Не удалось создать объект: " + e.getMessage());
            return null;
        }
    }
}
