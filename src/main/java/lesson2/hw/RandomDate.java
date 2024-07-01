package lesson2.hw;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// FIXME: Заставить аннотацию ставится только над полями
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface RandomDate {

    long min() default 1704067200000L; // 1 января 2024 года UTC0

    long max() default 1735689600000L; // 1 января 2025 года UTC0

    String zone() default "Europe/Moscow";
}
