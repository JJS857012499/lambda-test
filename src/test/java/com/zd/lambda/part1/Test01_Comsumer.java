package com.zd.lambda.part1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by 江俊升 on 2019/3/11.
 */
public class Test01_Comsumer {


    /**
     * Write a consumer that clears the list it consumes.
     */
    @Test
    public void consumer_1() {

        Consumer<List<String>> consumer = List::clear;

        List<String> list =
                new ArrayList<>(Arrays.asList("a", "b", "c"));

        consumer.accept(list);

        assertThat(list).isEmpty();
    }

    /**
     * Write a consumer that first consumes the list with the
     * consumer c1, and then consumers it with the second consumer
     * c2.
     */
    @Test
    public void consumer_2() {
        Consumer<List<String>> c1 = list -> list.add("first");
        Consumer<List<String>> c2 = list -> list.add("second");

        Consumer<List<String>> consumer = c1.andThen(c2);

        List<String> list =
                new ArrayList<>(Arrays.asList("a", "b", "c"));

        consumer.accept(list);
        assertThat(list).containsExactly("a", "b", "c", "first", "second");
    }


}
