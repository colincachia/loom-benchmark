package com.ixaris.benchmark.suite;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;

import java.util.regex.Pattern;

@Warmup(iterations = 1)
@Fork(value = 1)
@Measurement(iterations = 2)
@BenchmarkMode({Mode.Throughput, Mode.AverageTime})
public class StringBenchmark {

    private static final String REGEX = "cat\\w+";
    private static final String TEXT = "dogcatdog";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    @Benchmark
    public String stringBenchmark() {
        final String s = TEXT.replaceAll(REGEX, "");
        return s;
    }

    @Benchmark
    public String matcherBenchmark() {
        final String s = PATTERN.matcher(TEXT).replaceAll("");
        return s;
    }

}
