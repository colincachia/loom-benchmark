# Loom Benchmark
### A tiny application developed to run benchmarks with the :coffee: Java [Project Loom](https://openjdk.java.net/projects/loom/) Early Access Builds available with JDK15+.

## Description

This application uses the [Java Microbenchmark Harness (JMH)](https://github.com/openjdk/jmh) available within the OpenJDK for benchmarking.
In order to make use of Virtual Threads, the [Project Loom early access build jdk](https://jdk.java.net/loom/) must be downloaded.

This app currently provides two types of tasks: a Task containing IO work (a network http call) and a Non-IO Task (generation or public/private key combination).
The benchmarks run these tasks with two types of threads, i.e. Virtual Threads provided with Project Loom and the traditional Kernel threads.
These benchmarks are currently testing for throughput and the average time per operation, although more benchmarks can be added with JMH.

## Running the benchmarks

Currently, it is not possible to run the benchmarks directly via maven, so we need to run the resulting jar file as below.

1. Compile the project with `mvnw clean install`
2. Change directory to `benchmark-suite/target`
3. Run `java -jar benchmarks.jar` (add -help for other options, ex using a profiler)

## Sample Result Output

The following results were extracted whilst running the benchmarks with the different profilers on a machine having the following specs:

```
OS Name:                   Microsoft Windows 10 Home
OS Version:                10.0.19041 N/A Build 19041
OS Manufacturer:           Microsoft Corporation
System Manufacturer:       HUAWEI
System Model:              KLVL-WXX9
System Type:               x64-based PC
Processor(s):              1 Processor(s) Installed.
                           [01]: AMD64 Family 23 Model 96 Stepping 1 AuthenticAMD ~2900 Mhz
BIOS Version:              HUAWEI 1.06, 14/09/2020
Total Physical Memory:     15,741 MB
Available Physical Memory: 7,848 MB
Virtual Memory: Max Size:  18,173 MB
Virtual Memory: Available: 7,686 MB
Virtual Memory: In Use:    10,487 MB

openjdk version "16-loom" 2021-03-16
OpenJDK Runtime Environment (build 16-loom+9-316)
OpenJDK 64-Bit Server VM (build 16-loom+9-316, mixed mode, sharing)
```

### Profiler: `gc` (GC profiling via standard MBeans)

```
Benchmark                                                        (taskType)  (threadType)   Mode  Cnt         Score         Error   Units
SimpleBenchmark.benchmark                                                io       virtual  thrpt    5        14.020 ?      0.547   ops/s
SimpleBenchmark.benchmark:搽c.alloc.rate                                 io       virtual  thrpt    5       213.298 ?      8.022  MB/sec
SimpleBenchmark.benchmark:搽c.alloc.rate.norm                            io       virtual  thrpt    5  16812448.026 ?   1263.927    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Eden_Space                        io       virtual  thrpt    5         8.109 ?      6.152  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Eden_Space.norm                   io       virtual  thrpt    5    639353.978 ? 490159.596    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Old_Gen                           io       virtual  thrpt    5       239.784 ?     17.481  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Old_Gen.norm                      io       virtual  thrpt    5  18900282.173 ?1218586.614    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Survivor_Space                    io       virtual  thrpt    5         0.004 ?      0.001  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Survivor_Space.norm               io       virtual  thrpt    5       298.374 ?     99.449    B/op
SimpleBenchmark.benchmark:搽c.count                                      io       virtual  thrpt    5       143.000                counts
SimpleBenchmark.benchmark:搽c.time                                       io       virtual  thrpt    5       221.000                    ms
SimpleBenchmark.benchmark                                                io        kernel  thrpt    5        12.726 ?      8.391   ops/s
SimpleBenchmark.benchmark:搽c.alloc.rate                                 io        kernel  thrpt    5         0.218 ?      0.115  MB/sec
SimpleBenchmark.benchmark:搽c.alloc.rate.norm                            io        kernel  thrpt    5     19038.706 ?   2909.897    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Eden_Space                        io        kernel  thrpt    5        14.098 ?      8.958  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Eden_Space.norm                   io        kernel  thrpt    5   1224190.750 ? 148174.039    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Old_Gen                           io        kernel  thrpt    5       217.496 ?    128.355  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Old_Gen.norm                      io        kernel  thrpt    5  18917572.762 ?1721770.687    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Survivor_Space                    io        kernel  thrpt    5         0.003 ?      0.002  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Survivor_Space.norm               io        kernel  thrpt    5       315.439 ?    376.643    B/op
SimpleBenchmark.benchmark:搽c.count                                      io        kernel  thrpt    5       131.000                counts
SimpleBenchmark.benchmark:搽c.time                                       io        kernel  thrpt    5       193.000                    ms
SimpleBenchmark.benchmark                                             nonio       virtual  thrpt    5     13809.667 ?    330.375   ops/s
SimpleBenchmark.benchmark:搽c.alloc.rate                              nonio       virtual  thrpt    5       266.541 ?      6.433  MB/sec
SimpleBenchmark.benchmark:搽c.alloc.rate.norm                         nonio       virtual  thrpt    5     21269.038 ?      9.958    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Eden_Space                     nonio       virtual  thrpt    5       268.210 ?     15.980  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Eden_Space.norm                nonio       virtual  thrpt    5     21402.710 ?   1328.358    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Survivor_Space                 nonio       virtual  thrpt    5         0.010 ?      0.006  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Survivor_Space.norm            nonio       virtual  thrpt    5         0.774 ?      0.479    B/op
SimpleBenchmark.benchmark:搽c.count                                   nonio       virtual  thrpt    5       172.000                counts
SimpleBenchmark.benchmark:搽c.time                                    nonio       virtual  thrpt    5        84.000                    ms
SimpleBenchmark.benchmark                                             nonio        kernel  thrpt    5      5424.927 ?    163.128   ops/s
SimpleBenchmark.benchmark:搽c.alloc.rate                              nonio        kernel  thrpt    5         2.142 ?      0.183  MB/sec
SimpleBenchmark.benchmark:搽c.alloc.rate.norm                         nonio        kernel  thrpt    5       434.820 ?     30.206    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Eden_Space                     nonio        kernel  thrpt    5      5166.541 ?    156.322  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Eden_Space.norm                nonio        kernel  thrpt    5   1048781.716 ?   9919.975    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Survivor_Space                 nonio        kernel  thrpt    5         0.020 ?      0.020  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Survivor_Space.norm            nonio        kernel  thrpt    5         4.053 ?      4.070    B/op
SimpleBenchmark.benchmark:搽c.count                                   nonio        kernel  thrpt    5       873.000                counts
SimpleBenchmark.benchmark:搽c.time                                    nonio        kernel  thrpt    5       775.000                    ms
SimpleBenchmark.benchmark                                                io       virtual   avgt    5         0.147 ?      0.009    s/op
SimpleBenchmark.benchmark:搽c.alloc.rate                                 io       virtual   avgt    5       206.445 ?     12.251  MB/sec
SimpleBenchmark.benchmark:搽c.alloc.rate.norm                            io       virtual   avgt    5  16812647.978 ?   2429.801    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Eden_Space                        io       virtual   avgt    5         9.096 ?      7.727  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Eden_Space.norm                   io       virtual   avgt    5    740682.211 ? 623765.720    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Old_Gen                           io       virtual   avgt    5       232.093 ?     28.890  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Old_Gen.norm                      io       virtual   avgt    5  18900927.338 ?1983747.169    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Survivor_Space                    io       virtual   avgt    5         0.004 ?      0.004  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Survivor_Space.norm               io       virtual   avgt    5       292.539 ?    337.605    B/op
SimpleBenchmark.benchmark:搽c.count                                      io       virtual   avgt    5       140.000                counts
SimpleBenchmark.benchmark:搽c.time                                       io       virtual   avgt    5       222.000                    ms
SimpleBenchmark.benchmark                                                io        kernel   avgt    5         0.145 ?      0.007    s/op
SimpleBenchmark.benchmark:搽c.alloc.rate                                 io        kernel   avgt    5         0.231 ?      0.025  MB/sec
SimpleBenchmark.benchmark:搽c.alloc.rate.norm                            io        kernel   avgt    5     18507.979 ?   1573.676    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Eden_Space                        io        kernel   avgt    5        11.783 ?      4.470  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Eden_Space.norm                   io        kernel   avgt    5    945071.988 ? 363371.347    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Old_Gen                           io        kernel   avgt    5       235.342 ?     20.266  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Old_Gen.norm                      io        kernel   avgt    5  18872869.399 ?1375709.148    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Survivor_Space                    io        kernel   avgt    5         0.003 ?      0.002  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Survivor_Space.norm               io        kernel   avgt    5       272.535 ?    187.166    B/op
SimpleBenchmark.benchmark:搽c.count                                      io        kernel   avgt    5       140.000                counts
SimpleBenchmark.benchmark:搽c.time                                       io        kernel   avgt    5       231.000                    ms
SimpleBenchmark.benchmark                                             nonio       virtual   avgt    5        ? 10??                  s/op
SimpleBenchmark.benchmark:搽c.alloc.rate                              nonio       virtual   avgt    5       269.177 ?     11.576  MB/sec
SimpleBenchmark.benchmark:搽c.alloc.rate.norm                         nonio       virtual   avgt    5     21349.107 ?     22.475    B/op
SimpleBenchmark.benchmark:搽c.churn.Compressed_Class_Space            nonio       virtual   avgt    5        ? 10??                MB/sec
SimpleBenchmark.benchmark:搽c.churn.Compressed_Class_Space.norm       nonio       virtual   avgt    5         0.004 ?      0.039    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Eden_Space                     nonio       virtual   avgt    5       272.264 ?     22.586  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Eden_Space.norm                nonio       virtual   avgt    5     21595.151 ?   1810.280    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Old_Gen                        nonio       virtual   avgt    5         0.002 ?      0.021  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Old_Gen.norm                   nonio       virtual   avgt    5         0.193 ?      1.659    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Survivor_Space                 nonio       virtual   avgt    5         0.011 ?      0.023  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Survivor_Space.norm            nonio       virtual   avgt    5         0.880 ?      1.839    B/op
SimpleBenchmark.benchmark:搽c.churn.Metaspace                         nonio       virtual   avgt    5        ? 10??                MB/sec
SimpleBenchmark.benchmark:搽c.churn.Metaspace.norm                    nonio       virtual   avgt    5         0.021 ?      0.177    B/op
SimpleBenchmark.benchmark:搽c.count                                   nonio       virtual   avgt    5       207.000                counts
SimpleBenchmark.benchmark:搽c.time                                    nonio       virtual   avgt    5       122.000                    ms
SimpleBenchmark.benchmark                                             nonio        kernel   avgt    5        ? 10??                 s/op
SimpleBenchmark.benchmark:搽c.alloc.rate                              nonio        kernel   avgt    5         2.187 ?      0.184  MB/sec
SimpleBenchmark.benchmark:搽c.alloc.rate.norm                         nonio        kernel   avgt    5       438.778 ?     27.551    B/op
SimpleBenchmark.benchmark:搽c.churn.Compressed_Class_Space            nonio        kernel   avgt    5        ? 10??                MB/sec
SimpleBenchmark.benchmark:搽c.churn.Compressed_Class_Space.norm       nonio        kernel   avgt    5         0.004 ?      0.032    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Eden_Space                     nonio        kernel   avgt    5      5192.899 ?    384.540  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Eden_Space.norm                nonio        kernel   avgt    5   1041814.281 ?  53819.108    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Old_Gen                        nonio        kernel   avgt    5         0.011 ?      0.092  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Old_Gen.norm                   nonio        kernel   avgt    5         2.166 ?     18.649    B/op
SimpleBenchmark.benchmark:搽c.churn.G1_Survivor_Space                 nonio        kernel   avgt    5         0.021 ?      0.050  MB/sec
SimpleBenchmark.benchmark:搽c.churn.G1_Survivor_Space.norm            nonio        kernel   avgt    5         4.272 ?      9.820    B/op
SimpleBenchmark.benchmark:搽c.churn.Metaspace                         nonio        kernel   avgt    5        ? 10??                MB/sec
SimpleBenchmark.benchmark:搽c.churn.Metaspace.norm                    nonio        kernel   avgt    5         0.018 ?      0.157    B/op
SimpleBenchmark.benchmark:搽c.count                                   nonio        kernel   avgt    5       927.000                counts
SimpleBenchmark.benchmark:搽c.time                                    nonio        kernel   avgt    5       818.000                    ms
```

### Profiler: `hs_thr` (HotSpot (tm) threading subsystem via implementation-specific MBeans)

```
Benchmark                                   (taskType)  (threadType)   Mode  Cnt       Score      Error    Units
SimpleBenchmark.benchmark                           io       virtual  thrpt    5      14.254 ±    0.944    ops/s
SimpleBenchmark.benchmark:·threads.alive            io       virtual  thrpt    5      29.400 ±    5.166  threads
SimpleBenchmark.benchmark:·threads.daemon           io       virtual  thrpt    5      28.400 ±    5.166  threads
SimpleBenchmark.benchmark:·threads.started          io       virtual  thrpt    5      30.000             threads
SimpleBenchmark.benchmark                           io        kernel  thrpt    5      14.319 ±    0.872    ops/s
SimpleBenchmark.benchmark:·threads.alive            io        kernel  thrpt    5      13.400 ±    5.166  threads
SimpleBenchmark.benchmark:·threads.daemon           io        kernel  thrpt    5      12.400 ±    5.166  threads
SimpleBenchmark.benchmark:·threads.started          io        kernel  thrpt    5     884.000             threads
SimpleBenchmark.benchmark                        nonio       virtual  thrpt    5   13275.982 ± 1799.891    ops/s
SimpleBenchmark.benchmark:·threads.alive         nonio       virtual  thrpt    5      25.000 ±    0.001  threads
SimpleBenchmark.benchmark:·threads.daemon        nonio       virtual  thrpt    5      24.000 ±    0.001  threads
SimpleBenchmark.benchmark:·threads.started       nonio       virtual  thrpt    5      25.000             threads
SimpleBenchmark.benchmark                        nonio        kernel  thrpt    5    5043.919 ±  387.032    ops/s
SimpleBenchmark.benchmark:·threads.alive         nonio        kernel  thrpt    5       9.000 ±    0.001  threads
SimpleBenchmark.benchmark:·threads.daemon        nonio        kernel  thrpt    5       8.000 ±    0.001  threads
SimpleBenchmark.benchmark:·threads.started       nonio        kernel  thrpt    5  295677.000             threads
SimpleBenchmark.benchmark                           io       virtual   avgt    5       0.146 ±    0.012     s/op
SimpleBenchmark.benchmark:·threads.alive            io       virtual   avgt    5      29.400 ±    5.166  threads
SimpleBenchmark.benchmark:·threads.daemon           io       virtual   avgt    5      28.400 ±    5.166  threads
SimpleBenchmark.benchmark:·threads.started          io       virtual   avgt    5      30.000             threads
SimpleBenchmark.benchmark                           io        kernel   avgt    5       0.148 ±    0.009     s/op
SimpleBenchmark.benchmark:·threads.alive            io        kernel   avgt    5      13.600 ±    3.444  threads
SimpleBenchmark.benchmark:·threads.daemon           io        kernel   avgt    5      12.600 ±    3.444  threads
SimpleBenchmark.benchmark:·threads.started          io        kernel   avgt    5     822.000             threads
SimpleBenchmark.benchmark                        nonio       virtual   avgt    5      ? 10??                s/op
SimpleBenchmark.benchmark:·threads.alive         nonio       virtual   avgt    5      25.000 ±    0.001  threads
SimpleBenchmark.benchmark:·threads.daemon        nonio       virtual   avgt    5      24.000 ±    0.001  threads
SimpleBenchmark.benchmark:·threads.started       nonio       virtual   avgt    5      25.000             threads
SimpleBenchmark.benchmark                        nonio        kernel   avgt    5      ? 10?³                s/op
SimpleBenchmark.benchmark:·threads.alive         nonio        kernel   avgt    5       9.000 ±    0.001  threads
SimpleBenchmark.benchmark:·threads.daemon        nonio        kernel   avgt    5       8.000 ±    0.001  threads
SimpleBenchmark.benchmark:·threads.started       nonio        kernel   avgt    5  302563.000             threads

``` 

### Profiler: `stack` (Simple and naive Java stack profiler)

#### `taskType=io`/`threadType=virtual`
```
....[Thread state distributions]....................................................................
 68.5%         WAITING
 23.2%         TIMED_WAITING
  8.3%         RUNNABLE

....[Thread state: WAITING].........................................................................
 68.5% 100.0% jdk.internal.misc.Unsafe.park

....[Thread state: TIMED_WAITING]...................................................................
 19.2%  82.6% jdk.internal.misc.Unsafe.park
  4.0%  17.4% java.lang.Object.wait0

....[Thread state: RUNNABLE]........................................................................
  4.0%  48.6% <stack is empty, everything is filtered?>
  4.0%  47.9% sun.nio.ch.WEPoll.wait
  0.2%   2.2% java.lang.Continuation.run
  0.0%   0.1% jdk.internal.misc.Unsafe.allocateInstance
  0.0%   0.0% jdk.internal.net.http.HttpClientImpl$SelectorManager.run
  0.0%   0.0% jdk.internal.net.http.SocketTube$InternalReadPublisher$InternalReadSubscription.read
  0.0%   0.0% java.lang.invoke.DirectMethodHandle$Holder.newInvokeSpecial
  0.0%   0.0% sun.nio.ch.WEPollSelectorImpl.processUpdateQueue
  0.0%   0.0% sun.nio.ch.SelectorImpl.poll
  0.0%   0.0% java.util.HashMap.hash
  0.1%   1.1% <other>
```

#### `taskType=io`/`threadType=virtual`
```
....[Thread state distributions]....................................................................
 44.8%         TIMED_WAITING
 36.3%         WAITING
 19.0%         RUNNABLE

....[Thread state: TIMED_WAITING]...................................................................
 35.5%  79.4% jdk.internal.misc.Unsafe.park
  9.2%  20.6% java.lang.Object.wait0

....[Thread state: WAITING].........................................................................
 18.3%  50.4% java.lang.Object.wait0
 18.0%  49.6% jdk.internal.misc.Unsafe.park

....[Thread state: RUNNABLE]........................................................................
  9.3%  48.9% <stack is empty, everything is filtered?>
  9.1%  48.0% sun.nio.ch.WEPoll.wait
  0.2%   1.0% java.nio.HeapByteBuffer.<init>
  0.0%   0.1% sun.nio.ch.SocketDispatcher.writev0
  0.0%   0.1% sun.nio.ch.SocketDispatcher.read0
  0.0%   0.1% jdk.internal.misc.Unsafe.allocateInstance
  0.0%   0.0% java.util.stream.AbstractPipeline.<init>
  0.0%   0.0% jdk.internal.net.http.HttpClientImpl$SelectorManager.run
  0.0%   0.0% java.lang.invoke.DirectMethodHandle$Holder.newInvokeSpecial
  0.0%   0.0% jdk.internal.net.http.MultiExchange.requestFilters
  0.3%   1.7% <other>
```

#### `taskType=nonio`/`threadType=virtual`
```
....[Thread state distributions]....................................................................
 81.1%         WAITING
 13.8%         RUNNABLE
  5.0%         TIMED_WAITING

....[Thread state: WAITING].........................................................................
 81.1% 100.0% jdk.internal.misc.Unsafe.park

....[Thread state: RUNNABLE]........................................................................
  8.0%  58.1% java.lang.Continuation.run
  5.0%  36.2% <stack is empty, everything is filtered?>
  0.5%   3.6% jdk.internal.misc.Unsafe.unpark
  0.1%   0.5% jdk.internal.misc.Unsafe.park
  0.1%   0.4% java.util.concurrent.ForkJoinPool.scan
  0.0%   0.2% java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await
  0.0%   0.2% java.lang.Thread.interrupted
  0.0%   0.1% com.ixaris.benchmark.suite.SimpleBenchmark.benchmark
  0.0%   0.1% com.ixaris.benchmark.suite.generated.SimpleBenchmark_benchmark_jmhTest.benchmark_thrpt_jmhStub
  0.0%   0.1% java.util.concurrent.ForkJoinPool.runWorker
  0.1%   0.4% <other>

....[Thread state: TIMED_WAITING]...................................................................
  5.0%  99.6% java.lang.Object.wait0
  0.0%   0.4% jdk.internal.misc.Unsafe.park
```

#### `taskType=io`/`threadType=kernel`
```
Stack profiler:

....[Thread state distributions]....................................................................
 50.7%         RUNNABLE
 32.1%         WAITING
 17.1%         TIMED_WAITING

....[Thread state: RUNNABLE]........................................................................
 31.9%  62.9% <stack is empty, everything is filtered?>
  5.7%  11.2% java.math.BigInteger.oddModPow
  3.3%   6.6% sun.nio.ch.SocketDispatcher.write0
  1.6%   3.1% java.lang.Thread.start0
  1.0%   2.0% java.security.AccessController.getStackAccessControlContext
  0.9%   1.8% java.lang.Throwable.fillInStackTrace
  0.6%   1.1% java.lang.Object.wait0
  0.5%   0.9% java.security.Provider.getService
  0.4%   0.8% java.lang.Class.forName0
  0.3%   0.6% java.security.Provider.newInstanceUtil
  4.6%   9.0% <other>

....[Thread state: WAITING].........................................................................
 30.8%  96.0% java.lang.Object.wait0
  1.3%   4.0% jdk.internal.misc.Unsafe.park

....[Thread state: TIMED_WAITING]...................................................................
 17.1% 100.0% java.lang.Object.wait0
```