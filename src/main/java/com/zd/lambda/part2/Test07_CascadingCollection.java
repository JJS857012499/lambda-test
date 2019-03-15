package com.zd.lambda.part2;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by 江俊升 on 2019/3/15.
 */
public class Test07_CascadingCollection {

    private List<String> alphabet =
            List.of("alfa", "bravo", "charlie", "delta", "echo",
                    "foxtrot", "golf", "hotel", "india", "juliet",
                    "kilo", "lima", "mike", "november", "oscar",
                    "papa", "quebec", "romeo", "sierra", "tango",
                    "uniform", "victor", "whiskey", "x-ray", "yankee",
                    "zulu");

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
            "To eat the world's due, by the grave and thee.");

    @Test
    public void cascadingCollection_1() {
        Map<String, Long> map = sonnet.stream()
                .collect(Collectors.groupingBy(
                        line -> line.substring(0, 1),
                        Collectors.counting()
                ));
        assertThat(map.size()).isEqualTo(8);
        assertThat(map).containsExactly(
                Map.entry("P", 1L),
                Map.entry("A", 2L),
                Map.entry("B", 2L),
                Map.entry("T", 4L),
                Map.entry("F", 2L),
                Map.entry("W", 1L),
                Map.entry("H", 1L),
                Map.entry("M", 1L)
        );
    }

    @Test
    public void cascadingCollection_2() {

        Map<String, List<Integer>> map = sonnet.stream()
                .collect(Collectors.groupingBy(
                        line -> line.substring(0, 1),
                        Collectors.mapping(String::length, Collectors.toList())
                ));

        assertThat(map.size()).isEqualTo(8);
        assertThat(map).containsExactly(
                Map.entry("P", List.of(40)),
                Map.entry("A", List.of(36, 46)),
                Map.entry("B", List.of(40, 45)),
                Map.entry("T", List.of(43, 46, 45, 46)),
                Map.entry("F", List.of(42, 53)),
                Map.entry("W", List.of(41)),
                Map.entry("H", List.of(38)),
                Map.entry("M", List.of(37)));
    }

    @Test
    public void cascadingCollectors_3() {
        var mapToFirstWordInASet =
                Collectors.mapping(
                        (String line) -> line.split(" +")[0],
                        Collectors.toSet()
                );

        Map<String, Set<String>> map = sonnet.stream()
                .collect(Collectors.groupingBy(
                        line -> line.substring(0, 1),
                        mapToFirstWordInASet
                ));
        assertThat(map.size()).isEqualTo(8);
        assertThat(map).containsExactly(
                Map.entry("P", Set.of("Pity")),
                Map.entry("A", Set.of("And", "And,")),
                Map.entry("B", Set.of("But")),
                Map.entry("T", Set.of("That", "Thy", "To", "Thou")),
                Map.entry("F", Set.of("Feed'st", "From")),
                Map.entry("W", Set.of("Within")),
                Map.entry("H", Set.of("His")),
                Map.entry("M", Set.of("Making"))
        );
    }

    @Test
    public void cascadingCollectors_4() {
        Map<String, Long> map = sonnet.stream()
                .flatMap(line -> expand(line).stream())
                .collect(Collectors.groupingBy(
                        letter -> letter,
                        Collectors.counting()
                ));

        map.forEach((letter, count) -> System.out.println(letter + " => " + count));
    }

    private List<String> expand(String s) {
        return s.codePoints()
                .mapToObj(codePoint -> Character.toString((char) codePoint))
                .collect(Collectors.toList());
    }


    @Test
    public void cascadingCollectors_4b() {

        Collector<String, ?, Map<String, Long>> collect = Collectors.flatMapping(
                line -> expand(line).stream(),
                Collectors.groupingBy(
                        letter -> letter,
                        Collectors.counting()
                )
        );

        Map<String, Long> map = sonnet.stream()
                .collect(collect);

        map.forEach((letter, count) -> System.out.println(letter + " => " + count));
    }


}
