package com.zd.lambda.part1.util;

import java.util.Objects;

/**
 * Created by 江俊升 on 2019/3/11.
 */
@FunctionalInterface
public interface Consumer<T> {

    void accept(T t);

    default Consumer<T> andThen(Consumer<T> after) {
        Objects.requireNonNull(after);
        return (T t) -> {
            accept(t);
            after.accept(t);
        };
    }
}
