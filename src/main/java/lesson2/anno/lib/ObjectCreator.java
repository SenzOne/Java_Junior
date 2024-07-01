package lesson2.anno.lib;

import lesson2.hw.RandomDateProcessor;

import java.lang.reflect.Constructor;

public class ObjectCreator {

    public static <T> T createObj(Class<T> tClass) {
        try {
            Constructor<T> constructor = tClass.getDeclaredConstructor();
//            constructor.setAccessible(true); // На случай если конструктор не публичный
            T obj = constructor.newInstance();
            RandomAnnotationProcessor.processAnnotation(obj);
            return obj;
        } catch (Exception e) {
            System.err.println("ничего не получилось");
            return null;
        }
    }
}
