package ru.ifmo.ctddev.nechaev.task7;

/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 23.05.13
 * Time: 4:08
 */

/**
 * Realisation Multi-Client
 * {@see Client}
 */
public class MultiplierClient extends Client<Integer, Integer> {
    public MultiplierClient(TaskRunner taskRunner, MultiplierTask task) {
        super(taskRunner, task);
    }

    public void start() {
        for (int i = 0; i < 100; i++) {
            String report = this.toString() + "\n" + "Value = " + i + "\n";
            try {
                Integer result = run(i);
                System.out.println(report + "Result is " + result + "\n");
            } catch (InterruptedException e) {
                System.out.println(report + "Task has been interrupted" + "\n");
            }
        }
    }

    public static void main(String[] args) {
        int clientCount = 3;
        final int threadCount = 4;
        final TaskRunner taskRunner = new TaskRunnerImpl(threadCount);
//        for (int i = 0; i < clientCount; i++) {
//            final int j = i + 1;
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    new MultiplierClient(taskRunner, new MultiplierTask(j)).start();
//                }
//            });
//            thread.isDaemon();
//            thread.start();
//        }
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                new MultiplierClient(taskRunner, new MultiplierTask(1)).start();
            }
        });
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                new MultiplierClient(taskRunner, new MultiplierTask(2)).start();
            }
        });

        A.setDaemon(true);
        B.setDaemon(true);
        A.start();
        B.start();

        A.interrupt();
        B.interrupt();
    }
}
