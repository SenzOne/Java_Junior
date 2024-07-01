package lesson2.anno.lib;

import java.lang.reflect.Constructor;

public class ObjectCreator {

    public static <T> T createObj(Class<T> tClass) {
        try {
//            Constructor<T> tConstructor = tClass.getDeclaredConstructor();
//            tConstructor.setAccessible(true);
//
//            T obj = tConstructor.newInstance();
//            RandomAnnotationProcessor.processAnnotation(obj);

            Constructor<T> constructor = tClass.getDeclaredConstructor();
            T obj = constructor.newInstance();
            RandomAnnotationProcessor.processAnnotation(obj);
            return obj;
        } catch (Exception e) {
            System.err.println("ничего не получилось");
            return null;
        }
    }
}
