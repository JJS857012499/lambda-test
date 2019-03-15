package com.zd.lambda.part2;

import org.junit.Test;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by 江俊升 on 2019/3/15.
 */
public class Test01_FlatMap {

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
    public void flatMap_1() {
        List<List<String>> result = alphabet.stream()
                .map(s -> expand(s))
                .collect(Collectors.toList());

        assertThat(result).containsExactly(
                List.of("a", "l", "f", "a"),
                List.of("b", "r", "a", "v", "o"),
                List.of("c", "h", "a", "r", "l", "i", "e"),
                List.of("d", "e", "l", "t", "a")
        );

    }

    private List<String> expand(String s) {
        return s.codePoints()
                .mapToObj(codePoint -> Character.toString((char)codePoint))
                .collect(Collectors.toList());
    }

    @Test
    public void flatMap_2(){

        Pattern pattern = Pattern.compile(" +");

        List<String> words = sonnet.stream()
                .flatMap(pattern::splitAsStream)
                .collect(Collectors.toList());

        assertThat(words.size()).isEqualTo(106);
        assertThat(words).contains("From", "fairest", "creatures", "we", "desire", "increase,");
    }




}
