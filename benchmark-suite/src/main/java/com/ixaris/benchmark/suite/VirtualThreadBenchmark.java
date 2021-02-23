package com.ixaris.benchmark.suite;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadBenchmark extends AbstractBenchmark {

    private static final ExecutorService SERVICE = Executors.newVirtualThreadExecutor();

    @Override
    CompletableFuture<?> submit(final Callable<?> task) {
        return SERVICE.submitTask(task);
    }
}
