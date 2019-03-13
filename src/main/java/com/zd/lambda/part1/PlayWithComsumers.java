package com.zd.lambda.part1;

import com.zd.lambda.part1.util.Consumer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 江俊升 on 2019/3/11.
 */
public class PlayWithComsumers {

    public static void main(String[] args) {

        Consumer<String> consumer = System.out::println;

        consumer.accept("hello dev");

        Consumer<List<String>> c1 = list -> list.add("one");
        Consumer<List<String>> c2 = list -> list.add("two");

        Consumer<List<String>> c3 = c1.andThen(c2);

        List<String> strings = new ArrayList<>();
        strings.add("zero");
        c3.accept(strings);
        System.out.println("strings=" + strings);

    }

}
