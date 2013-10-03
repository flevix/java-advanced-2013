package ru.ifmo.ctddev.nechaev.task6;

/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 19.05.13
 * Time: 20:48
 */

/**
 * {@see java.lang.Runnable}
 */
public class Worker implements Runnable {

    private int[][] A, B, C;
    private int n, first, last;

    /**
     *
     * @param n length one side square matrix
     * @param first first cell in range
     * @param last last cell in range
     * @param A first matrix
     * @param B second matrix
     * @param C result matrix
     */
    public Worker(int n, int first, int last, int[][] A, int[][] B, int[][] C) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.n = n;
        this.first = first;
        this.last = last;
    }

    /**
     * Calculating matrix cell [first..last)
     */
    @Override
    public void run() {
        for (int i = first; i < last; i++) {
            int r = i / n;
            int c = i % n;
            C[r][c] = 0;
            for (int k = 0; k < n; k++) {
                C[r][c] += A[r][k] * B[k][c];
            }
        }
    }
}
