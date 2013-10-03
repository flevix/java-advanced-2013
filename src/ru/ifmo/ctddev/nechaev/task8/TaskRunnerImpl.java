package ru.ifmo.ctddev.nechaev.task8;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 23.05.13
 * Time: 4:06
 */

/**
 * Implementation of {@link TaskRunner}
 */
public class TaskRunnerImpl implements TaskRunner {
    /**
     * Task executor
     * @link java.util.concurrent.ExecutorService
     */
    private ExecutorService executorService;

    /**
     * Construct a {@link java.util.concurrent.ExecutorService}
     * with fixed number of threads operating off a shared unbounded queue.
     * @param nThreads - the number of threads in the pool
     */
    public TaskRunnerImpl(int nThreads) {
        executorService = Executors.newFixedThreadPool(nThreads);
    }

    /**
     * Submits a value-returning task with it argument
     * for execution and returns the task's result upon successful completion.
     * @param task evaluating Task
     * @param value argument for {@code task}
     * @return Computes a result
     */
    @Override
    public <X, Y> X run(Task<X, Y> task, Y value) {
        Work<X, Y> work = new Work<>(task, value);
        X result = null;
        try {
            result = executorService.submit(work).get();
        } catch (InterruptedException | ExecutionException ex) {
            System.err.println(ex);
            Thread.currentThread().isInterrupted();
        }
        return result;
    }

    public void stop() {
        executorService.shutdown();
    }

    /**
     * Supporting class for creating Callable from {@code task} and it {@code value}
     * @see java.util.concurrent.Callable
     * @see Task
     * @param <X> type of return value
     * @param <Y> type of evaluating parameter
     */
    private class Work<X, Y>  implements Callable<X> {
        private final Task<X, Y> task;
        private final Y value;

        /**
         * Initialization {@code task} and {@code value} fields
         */
        private Work(Task<X, Y> task, Y value) {
            this.task = task;
            this.value = value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public X call() throws Exception {
            return task.run(value);
        }
    }
}
