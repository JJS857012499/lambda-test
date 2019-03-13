package com.zd.lambda.part1.util;

import java.util.Objects;

/**
 * Created by 江俊升 on 2019/3/11.
 */
@FunctionalInterface
public interface Predicate<T> {

    boolean test(T t);

    default Predicate<T> negate() {
        return (T t) -> !test(t);
    }

    default Predicate<T> and(Predicate<T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }

    default Predicate<T> xOr(Predicate<T> other) {
        Objects.requireNonNull(other);
        return t -> test(t) ^ other.test(t);
    }
}
