package ru.ifmo.ctddev.nechaev.task6;

/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 19.05.13
 * Time: 20:12
 */


public class Main {

    /**
     *
     * @param args $0 matrix size
     *             $1 count of threads
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java [package_name].Main [n], [m]");
            System.exit(1);
        }
        int n = 0;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException nfe) {
            System.err.println("Arguments should be positive integers");
            System.exit(1);
        }


        MatrixMultiplication matrixMultiplication = new MatrixMultiplication(n);
        for (int z = 1; z < 17; z++) {
            long startTime = System.nanoTime();
            try {
                matrixMultiplication.multiply(z);
            } catch (InterruptedException ie) {
                System.err.println("One of threads is interrupted");
                System.exit(1);
            }
            long estimatedTime = System.nanoTime() - startTime;
            matrixMultiplication.sumOfElements();
            System.out.println("Time: " + estimatedTime);
        }
    }
}
