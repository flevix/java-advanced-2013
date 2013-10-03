package ru.ifmo.ctddev.nechaev.task7;

/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 23.05.13
 * Time: 4:04
 */

public abstract class Client<X, Y> {
    /** Class for run tasks  */
    private TaskRunner taskRunner;
    /** Task for evaluating */
    private Task<X, Y> task;

    public Client(TaskRunner taskRunner, Task<X, Y> task) {
        this.taskRunner = taskRunner;
        this.task = task;
    }

    /**
     * Executing Task
     * @param value {@see Task}
     * @return evaluated Task
     * @throws InterruptedException
     */
    protected X run(Y value) throws InterruptedException {
        return taskRunner.run(task, value);
    }

    public abstract void start();

    public String toString() {
        return "Task: " + task.toString() + ", TaskRunner: " + taskRunner.toString();
    }
}
