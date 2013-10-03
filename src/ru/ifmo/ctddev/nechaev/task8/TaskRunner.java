package ru.ifmo.ctddev.nechaev.task8;

/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 23.05.13
 * Time: 4:04
 */

/**
 * Interface for TaskRunner
 * </p>
 * The task put in queue and enforced with input value,
 * when it comes time
 */

public interface TaskRunner {
    /**
     * @param task evaluating Task
     * @param value argument for {@code task}
     * @return result of evaluating Task
     */
    public <X, Y> X run(Task<X, Y> task, Y value);
}
