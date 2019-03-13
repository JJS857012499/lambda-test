package com.zd.lambda.part1.util;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by 江俊升 on 2019/3/13.
 */
public interface Validator<T> {


    Supplier<T> validate(T t);


    class ValidationException extends RuntimeException {
        public ValidationException(String message) {
            super(message);
        }
    }

    static <T> Validator<T> firstValidate(Predicate<T> p, String errorMessage) {
        Objects.requireNonNull(p);
        return t -> p.test(t) ?
                () -> {
                    ValidationException validationException = new ValidationException("Object is not valid");
                    validationException.addSuppressed(new IllegalArgumentException(errorMessage));
                    throw validationException;
                } :
                () -> t;
    }

    default Validator<T> thenValidate(Predicate<T> p, String errorMessage) {
        Objects.requireNonNull(p);
        return t -> () -> {
            try {
                validate(t).get(); // 1
                if (p.test(t)) {   // 2
                    ValidationException validationException = new ValidationException("Object is not valid");
                    validationException.addSuppressed(new IllegalArgumentException(errorMessage));
                    throw validationException;
                } else {
                    return t;
                }
            } catch (ValidationException validationException) {
                if (p.test(t)) { //如果是2处校验不通过，则会重复校验
                    validationException.addSuppressed(new IllegalArgumentException(errorMessage));
                    throw validationException;
                } else {
                    throw validationException;
                }
            }
        };
    }


}
