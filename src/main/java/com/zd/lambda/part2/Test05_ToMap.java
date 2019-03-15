package com.zd.lambda.part2;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by 江俊升 on 2019/3/15.
 */
public class Test05_ToMap {

    private List<String> alphabet = List.of("alfa", "bravo", "charlie", "delta");

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
    public void ToMap_1() {
        Map<String, String> result = alphabet.stream()
                .collect(Collectors.toMap(s -> s.substring(0, 1), s -> s));

        assertThat(result).containsExactly(
                Map.entry("a", "alfa"),
                Map.entry("b", "bravo"),
                Map.entry("c", "charlie"),
                Map.entry("d", "delta"));
    }


    /**
     * 抛出IllegalStateException：Duplicate key B
     */
    @Test
    @Ignore
    public void ToMap_2() {
        Map<String, String> result = sonnet.stream()
                .collect(Collectors.toMap(word -> word.substring(0, 1),
                        word -> word));
    }

    @Test
    public void ToMap_3() {
        Map<String, String> result = sonnet.stream()
                .collect(
                        Collectors.toMap(
                                line -> line.substring(0, 1),
                                line -> line,
                                (line1, line2) -> line1
                        ));

        assertThat(result.size()).isEqualTo(8);
        assertThat(result).contains(
                Map.entry("P", "Pity the world, or else this glutton be,"),
                Map.entry("A", "And only herald to the gaudy spring,"),
                Map.entry("B", "But as the riper should by time decease,"),
                Map.entry("T", "That thereby beauty's rose might never die,"),
                Map.entry("F", "From fairest creatures we desire increase,"),
                Map.entry("W", "Within thine own bud buriest thy content,"),
                Map.entry("H", "His tender heir might bear his memory:"),
                Map.entry("M", "Making a famine where abundance lies,")
        );

    }

    /**
     * 遇到重复的key，取第一个的内容
     */
    @Test
    public void toMap_4() {
        Map<String, String> result = sonnet.stream()
                .collect(
                        Collectors.toMap(
                                line -> line.substring(0, 1),
                                line -> line,
                                (line1, line2) -> line2
                        ));

        assertThat(result.size()).isEqualTo(8);
        assertThat(result).contains(
                Map.entry("P", "Pity the world, or else this glutton be,"),
                Map.entry("A", "And, tender churl, mak'st waste in niggarding:"),
                Map.entry("B", "But thou contracted to thine own bright eyes,"),
                Map.entry("T", "To eat the world's due, by the grave and thee."),
                Map.entry("F", "Feed'st thy light's flame with self-substantial fuel,"),
                Map.entry("W", "Within thine own bud buriest thy content,"),
                Map.entry("H", "His tender heir might bear his memory:"),
                Map.entry("M", "Making a famine where abundance lies,")
        );
    }

    /**
     * 如果遇到重复的key, 用换行符连接内容
     */
    @Test
    public void toMap_5() {
        Map<String, String> result = sonnet.stream()
                .collect(
                        Collectors.toMap(
                                line -> line.substring(0, 1),
                                line -> line, // Function.identity(),
                                (line1, line2) -> line1 + "\n" + line2
                        ));

        assertThat(result.size()).isEqualTo(8);
        assertThat(result).contains(
                Map.entry("P", "Pity the world, or else this glutton be,"),
                Map.entry("A", "And only herald to the gaudy spring,\n" +
                        "And, tender churl, mak'st waste in niggarding:"),
                Map.entry("B", "But as the riper should by time decease,\n" +
                        "But thou contracted to thine own bright eyes,"),
                Map.entry("T", "That thereby beauty's rose might never die,\n" +
                        "Thy self thy foe, to thy sweet self too cruel:\n" +
                        "Thou that art now the world's fresh ornament,\n" +
                        "To eat the world's due, by the grave and thee."),
                Map.entry("F", "From fairest creatures we desire increase,\n" +
                        "Feed'st thy light's flame with self-substantial fuel,"),
                Map.entry("W", "Within thine own bud buriest thy content,"),
                Map.entry("H", "His tender heir might bear his memory:"),
                Map.entry("M", "Making a famine where abundance lies,")
        );
    }

}
