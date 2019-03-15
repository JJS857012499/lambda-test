package com.zd.lambda.part2;

import org.junit.Test;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by 江俊升 on 2019/3/15.
 */
public class Test00_MapFilter {

    private List<String> alphabet =
            List.of("alfa", "bravo", "charlie", "delta", "echo",
                    "foxtrot", "golf", "hotel", "india", "juliet",
                    "kilo", "lima", "mike", "november", "oscar",
                    "papa", "quebec", "romeo", "sierra", "tango",
                    "uniform", "victor", "whiskey", "x-ray", "yankee",
                    "zulu");

    @Test
    public void mapFilter_01() {
        alphabet.stream()
                .map(String::toUpperCase)
                .filter(world -> world.length() == 6)
                .forEach(System.out::println);
    }

    @Test
    public void mapFilter_02() {
        BigInteger result = LongStream.rangeClosed(1, 21)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);
        assertThat(result).isEqualTo(new BigInteger("51090942171709440000"));
    }


}
