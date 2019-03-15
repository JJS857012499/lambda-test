package com.zd.lambda.part2;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by 江俊升 on 2019/3/15.
 */
public class Test08_StreamMaps {

    private List<String> sonnet = List.of(
            "From fairest creatures we desire increase,",
            "That thereby beauty's rose might never die,",
            "But as the riper should by time decease,",
            "His tender heir might bear his memory:",
            "But thou contracted to thine own bright eyes,",
            "Feed'st thy light's flame with self-substantial fuel,",
            "Making a famine where abundance lies,",
            "Thy self thy foe, to thy sweet self too cruel:",
            "Thou that art now the world's fresh ornament,",
            "And only herald to the gaudy spring,",
            "Within thine own bud buriest thy content,",
            "And, tender churl, mak'st waste in niggarding:",
            "Pity the world, or else this glutton be,",
            "To eat the world's due, by the grave and thee.",
            "to to thy");


    @Test
    public void streamMap_1() {
        Pattern pattern = Pattern.compile("[ ,':\\-]+");

        Map<String, Long> words = sonnet.stream()
                .map(String::toLowerCase)
                .flatMap(pattern::splitAsStream)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(
                                        word -> word,
                                        Collectors.counting()
                                ),
                                map -> Map.copyOf(map)
                        )
                );

        words.forEach((letter, count) -> System.out.println(letter + " => " + count));

        Map.Entry<String, Long> mostFrequentWord = words.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow();
        System.out.println("mostFrequentWord = " + mostFrequentWord);
    }

    @Test
    public void streamMap_1b() {
        Pattern pattern = Pattern.compile("[ ,':\\-]+");

        Collector<String, ?, Map<String, Long>> collector = Collectors.mapping(
                String::toLowerCase,
                Collectors.flatMapping(
                        pattern::splitAsStream,
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(
                                        word -> word,
                                        Collectors.counting()
                                ),
                                Map::copyOf
                        )
                )
        );

        Map<String, Long> words = sonnet.stream().collect(collector);

        words.forEach((letter, count) -> System.out.println(letter + "=>" + count));

        Collector<Map.Entry<String, Long>, ?, Map.Entry<String, Long>> collector1 = Collectors.collectingAndThen(
                Collectors.maxBy(Map.Entry.comparingByValue()),
                Optional::orElseThrow
        );

        Map.Entry<String, Long> mostFrequentWord = words.entrySet().stream()
                .collect(collector1);

        System.out.println("mostFrequentWord = " + mostFrequentWord);
    }
}
