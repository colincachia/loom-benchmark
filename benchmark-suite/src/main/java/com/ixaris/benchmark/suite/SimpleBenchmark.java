package com.ixaris.benchmark.suite;

import loombenchmark.task.IOTask;
import loombenchmark.task.NonIOTask;
import loombenchmark.task.Task;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Timeout;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.ThreadFactory;

@State(Scope.Benchmark)
@Timeout(time = 60)
@Warmup(time = 2)
@Fork(value = 1)
@BenchmarkMode({Mode.Throughput, Mode.AverageTime})
@Threads(2)
public class SimpleBenchmark {

    @Param({"virtual", "kernel"})
    private String threadType;

    @Param({"io", "nonio"})
    private String taskType;

    private ThreadFactory threadFactory;
    private Task task;

    @Setup
    public void setup() {
        switch (threadType) {
            case "virtual" -> threadFactory = Thread.builder().virtual().factory();
            case "kernel" -> threadFactory = Thread.builder().factory();
            default -> throw new IllegalStateException("Unknown thread type: " + threadType);
        }
        switch (taskType) {
            case "io" -> task = new IOTask();
            case "nonio" -> task = new NonIOTask();
            default -> throw new IllegalStateException("Unknown task type: " + taskType);
        }
    }

    @Benchmark
    public Thread benchmark() throws InterruptedException {
        final Thread thread = threadFactory.newThread(task.work());
        thread.start();
        thread.join();
        return thread;
    }
}
