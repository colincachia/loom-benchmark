package loombenchmark.task;

import java.util.concurrent.Callable;

public interface Task<T> {

    Callable<T> work();

}
