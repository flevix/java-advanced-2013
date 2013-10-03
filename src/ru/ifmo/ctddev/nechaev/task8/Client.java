package ru.ifmo.ctddev.nechaev.task8;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 23.05.13
 * Time: 4:04
 */

/**
 * Class for generating and executing their tasks using TaskRunner.
 */
public class Client implements Runnable {
    /**
     * {@link TaskRunner}
     */
    private TaskRunner taskRunner;
    /**
     * Random number generator for generation task value.
     */
    private Random random;
    /**
     * Client id
     */
    private int id;

    /**
     * Construct a Client with the given Task Runner and Id.
     * </p>
     * Initialize Random number generator
     * @param taskRunner task executor
     * @param id client id
     */
    public Client(TaskRunner taskRunner, int id) {
        this.taskRunner = taskRunner;
        this.id = id;
        random = new Random();
    }

    /**
     * Execution different tasks while current thread is
     * not interrupted
     */
    @Override
    public void run() {
        int i = 0;
        final int maxValue = 1000;
        while (!Thread.currentThread().isInterrupted()) {
            Task task = new TaskImpl("Task #" + i++);
            System.out.println(this.toString() + " " + task + " Result: " +
                    taskRunner.run(task, random.nextInt(maxValue) + id * maxValue));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Client #" + id;
    }

    /**
     * Implementation of Task
     * {@link Task}
     */
    private class TaskImpl implements Task<Integer, Integer> {
        /**
         * task name
         */
        private String name;

        /**
         * Initializing {@code name} field with the name
         * @param name name of the current task
         */
        TaskImpl(String name) {
            this.name = name;
        }

        /**
         * Run task for evaluate and return result
         * @param value parameter of function to be run
         * @return {@code value++}
         */
        @Override
        public Integer run(Integer value) {
            return value + 1;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return name;
        }
    }
}
