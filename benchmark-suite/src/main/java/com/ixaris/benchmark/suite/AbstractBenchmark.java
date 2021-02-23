package com.ixaris.benchmark.suite;

import loombenchmark.task.IOTask;
import loombenchmark.task.NonIOTask;
import loombenchmark.task.Task;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/*@State(Scope.Benchmark)
@Warmup(iterations = 10, time = 500, timeUnit = MILLISECONDS)
@Measurement(iterations = 5)
@Fork(value = 1)
@BenchmarkMode({Mode.Throughput, Mode.AverageTime})
@Threads(10)*/
public abstract class AbstractBenchmark {

    abstract CompletableFuture<?> submit(Callable<?> task);

//    @Param({"io", "nonio"})
    private String taskType;

    private Task<?> task;

    private static final AtomicInteger COUNT = new AtomicInteger(0);

//    @Setup
    public void setup() {
        switch (taskType) {
            case "io" -> task = new IOTask();
            case "nonio" -> task = new NonIOTask();
            default -> throw new IllegalStateException("Unknown task type: " + taskType);
        }
    }

//    @Benchmark
    public CompletableFuture<?> benchmark() throws InterruptedException, ExecutionException {
        System.out.println("ITER: " + COUNT.incrementAndGet());
        return submit(task.work());
    }
}
