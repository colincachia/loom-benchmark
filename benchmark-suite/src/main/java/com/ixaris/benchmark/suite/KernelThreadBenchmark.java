package com.ixaris.benchmark.suite;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Setup;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class KernelThreadBenchmark extends AbstractBenchmark {

//    @Param({"200", "1000", "10000"})
    private int threadPoolSize;

    private ExecutorService executorService;
    private Semaphore semaphore;

    @Setup
    public void threadPoolSetup() {
        executorService = Executors.newFixedThreadPool(threadPoolSize);
        semaphore = new Semaphore(threadPoolSize);
    }

    @Override
    CompletableFuture<?> submit(final Callable<?> task) {
        try {
            semaphore.acquire();
            return executorService.submitTask(task);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
