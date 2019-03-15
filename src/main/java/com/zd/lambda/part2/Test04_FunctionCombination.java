package com.zd.lambda.part2;

import org.junit.Test;

import java.util.List;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by 江俊升 on 2019/3/15.
 */
public class Test04_FunctionCombination {

    @Test
    public void functionCombination_01() {

        List<Predicate<String>> predicates = List.of(s -> s != null, s -> !s.isEmpty(), s -> s.length() < 5);

        Predicate<String> combinePredicate = predicates.stream().reduce(s -> true, Predicate::and);

        assertThat(combinePredicate.test("")).isFalse();
        assertThat(combinePredicate.test(null)).isFalse();
        assertThat(combinePredicate.test("Hello")).isFalse();
        assertThat(combinePredicate.test("java")).isTrue();
    }


    @Test
    public void functionCombination_02() {

        List<IntUnaryOperator> operators =
                List.of(i -> i + 1, i -> i = i * 2, i -> i + 3);

        IntUnaryOperator combineOperator = operators.stream().reduce(IntUnaryOperator.identity(), IntUnaryOperator::andThen);
        assertThat(combineOperator.applyAsInt(5)).isEqualTo(15);

    }


}
