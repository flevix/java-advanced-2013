package ru.ifmo.ctddev.nechaev.task7;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 23.05.13
 * Time: 4:06
 */

/**
 * Implementation of TaskRunner
 * {@see TaskRunner}
 */
public class TaskRunnerImpl implements TaskRunner{
    /** Array of threads  */
    private Thread[] threads;
    /** Synchronized queue for task */
    private BlockingQueue<Work> queue;

    private class Work<X, Y> {
        private final Task<X, Y> task;
        private final Y value;
        private boolean ready;
        private X result;

        private Work(Task<X, Y> task, Y value) {
            this.task = task;
            this.value = value;
            this.ready = false;
            this.result = null;
        }

        public void execute() {
            result = task.run(value);
            ready = true;
        }

        public boolean isReady() {
            return ready;
        }

        public X getResult() {
            return result;
        }
    }

    private class Runner implements Runnable {
        /** run task with specified parameters and notify client if task is done */
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    Work work = queue.take();
                    synchronized (work) {
                        work.execute();
                        work.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * TaskRunner constructor of threads
     * @param threadCount count of threads
     */
    public TaskRunnerImpl(int threadCount) {
        this.queue = new LinkedBlockingQueue<>();
        this.threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(new Runner());
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    /**
     *
     * @param task evaluating Task
     * @param value argument of {@code task}
     * @return result of executing Task
     * @throws InterruptedException
     */
    public <X, Y> X run(Task<X, Y> task, Y value) throws InterruptedException {
        Work<X, Y> work = new Work<>(task, value);
        queue.put(work);
        synchronized (work) {
            while (!work.isReady()) {
                work.wait();
            }
        }
        return work.getResult();
    }
}
