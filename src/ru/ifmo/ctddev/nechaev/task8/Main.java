package ru.ifmo.ctddev.nechaev.task8;

/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 05.06.13
 * Time: 21:11
 * <p/>
 * >:3
 * RAWR I'M A LION!
 * JESUS CHRIST IT'S A LION GET IN THE CAR!
 */

/**
 * How to use
 */
public class Main {
    public static void main(String[] args) {
        int clientCount = 8;
        final int threadCount = 4;
        TaskRunnerImpl taskRunner = new TaskRunnerImpl(threadCount);
        for (int i = 0; i < clientCount; i++) {
            new Thread(new Client(taskRunner, i + 1)).start();
        }
    }
}