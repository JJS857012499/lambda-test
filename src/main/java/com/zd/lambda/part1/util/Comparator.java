package com.zd.lambda.part1.util;

import java.util.Objects;
import java.util.function.Function;

/**
 * Created by 江俊升 on 2019/3/11.
 */
@FunctionalInterface
public interface Comparator<T> {

    int compare(T o1, T o2);


    static <T, U extends Comparable<? super U>> Comparator<T> comparing(Function<T, U> keyExtractor) {
        Objects.requireNonNull(keyExtractor);
        return (T o1, T o2) -> {
            U u1 = keyExtractor.apply(o1);
            U u2 = keyExtractor.apply(o2);
            return u1.compareTo(u2);
        };
    }

    default <U extends Comparable<? super U>> Comparator<T> thenComparing(Function<T, U> keyExtractor) {
        Objects.requireNonNull(keyExtractor);
        return (o1, o2) -> {
            int cmp = this.compare(o1, o2);
            if (cmp == 0) {
                Comparator<T> other = comparing(keyExtractor);
                return other.compare(o1, o2);
            } else {
                return cmp;
            }
        };
    }


    static <T> Comparator<T> nullsLast(Comparator<T> cmp) {
        Objects.requireNonNull(cmp);
        return (o1, o2) -> {
            if (o1 == o2) {
                return 0;
            } else if (o1 == null) {
                return 42;
            } else if (o2 == null) {
                return -41;
            } else {
                return cmp.compare(o1, o2);
            }
        };
    }

    default Comparator<T> reversed() {
        return (o1, o2) -> compare(o2, o1);  //不使用减号
    }
}
