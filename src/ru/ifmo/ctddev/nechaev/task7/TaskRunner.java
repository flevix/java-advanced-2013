package ru.ifmo.ctddev.nechaev.task7;

/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 23.05.13
 * Time: 4:04
 */

/**
 * Interface for TaskRunner
 * put task into a queue fot it turn and execute it
 */
public interface TaskRunner {
    /**
     *
     * @param task evaluating Task
     * @param value argument of {@code task}
     * @return result of evaluating Task
     * @throws InterruptedException
     */
    public <X, Y> X run(Task<X, Y> task, Y value) throws InterruptedException;
}
