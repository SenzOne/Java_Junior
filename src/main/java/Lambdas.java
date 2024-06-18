import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

public class Lambdas {
    public static void main(String[] args) {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("hello!");
//            }
//        };
//
//        runnable.run();


        // () -> ()
        Runnable runnable = () -> System.out.println("hi");

        //consumer (потребитель)
        // принимает T и ничего не возвращает
        // (T) -> ()
        Consumer<String> stringConsumer = System.out::println;

        // supplier (поставщик)
        // ничего не принимает, возвращает T
        // () -> T
        Supplier<Integer> randIntProvider = () -> ThreadLocalRandom.current().nextInt(0, 10);
//        System.out.println(randIntProvider.get());

        // function
        // (T) -> R
        Function<String, Integer> stringLengthFunction = String::length;
        System.out.println(stringLengthFunction.apply("hello"));
        Function<String, String> toUpperCaseFunction = s -> s.toUpperCase();
        System.out.println(toUpperCaseFunction.apply("Hello")); //

        // function у которого аргументы одинаковые по сути есть
        // UnaryOperator
        UnaryOperator<String> toUpperCase = s -> s.toUpperCase(Locale.ENGLISH);
        System.out.println(toUpperCase.apply("hello"));


        // s -> boolean - predicate тестер (фильтр)
        Predicate<Integer> isEvenPredicate = x -> x % 2 == 0;
        System.out.println(isEvenPredicate.test(0)); // true
        System.out.println(isEvenPredicate.test(2)); // true
        System.out.println(isEvenPredicate.test(3)); // false

        //  comparator это тоже функциональный интерфейс
        // изначальная сортировка по алфавиту
        List<String> strings = new ArrayList<>(List.of("java", "c#", "python"));
        Collections.sort(strings, (a, b) -> a.length() - b.length()); // [c#, java, python]
        System.out.println(strings);


        Runnable updater = foo();
        updater.run();
    }

    static Runnable foo() {
        String x = "value";
        Runnable updater = () -> {
            System.out.println(x);
//            x = "newValue";
        };

        return updater;
    }
}

