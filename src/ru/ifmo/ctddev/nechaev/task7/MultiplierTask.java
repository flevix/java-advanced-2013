package ru.ifmo.ctddev.nechaev.task7;

/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 23.05.13
 * Time: 4:09
 */

/**
 * Multi-Task
 * {@see Task}
 */
public class MultiplierTask implements Task<Integer, Integer>{
    private final int x;

    public MultiplierTask(int x) {
        this.x = x;
    }

    public Integer run(Integer value) {
        return x * value;
    }

    public String toString() {
        return "Multiplier(" + x + ")";
    }
}
