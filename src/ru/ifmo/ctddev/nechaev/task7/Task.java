package ru.ifmo.ctddev.nechaev.task7;

/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 23.05.13
 * Time: 4:05
 */

/**
 * Interface for evaluating Task
 * @param <X> type of return value
 * @param <Y> type of evaluating parameter
 */
public interface Task<X, Y> {
    /**
     *
     * @param value parameter of function to be run
     * @return result of evaluating function
     */
    public X run(Y value);
}
