package com.zd.lambda.part2;

import org.junit.Test;

import java.math.BigInteger;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by 江俊升 on 2019/3/15.
 */
public class Test03_Reduction {


    @Test
    public void reduction_01() {
        BigInteger result = LongStream.rangeClosed(1, 21)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);
        assertThat(result).isEqualTo(new BigInteger("51090942171709440000"));
    }

}
