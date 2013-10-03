package ru.ifmo.ctddev.nechaev.task1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 21.02.13
 * Time: 0:28
 */

public class Matrix {
    private double[][] data;
    public int r, c;
    private StringTokenizer st;

    public Matrix(File file) throws IOException {
        try (Scanner scanner = new Scanner(file)) {
            data = read(scanner);
        }
    }

    public Matrix(Scanner scanner) throws IOException {
         data = read(scanner);
    }

    public Matrix(double[][] source) {
        checkNullException(source);
        checkArraySizeException(source);
        data = new double[source.length][];
        for (int i = 0; i < source.length; i++) {
            data[i] = source[i].clone();
        }
        r = data.length;
        c = data[0].length;
    }

    public Matrix(int n, int m) {
        checkNegationSize(n, m);
        data = new double[n][m];
        r = n;
        c = m;
    }

    public void write(File file) throws IOException {
        PrintWriter out = new PrintWriter(file);
        try {
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    out.print(data[i][j] + " ");
                }
                out.println();
            }
        } finally {
        }
        out.close();
        if (out.checkError()) {
            throw new IOException("Some errors with write");
        }
    }

    public Matrix add(Matrix other) throws MatrixExceptions {
        checkNullException(other.data);
        checkAddAndSubstractSizeException(r, c, other.r, other.c);
        Matrix temp = new Matrix(r, c);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                temp.data[i][j] = data[i][j] + other.data[i][j];
            }
        }
        return temp;
    }

    public Matrix subtract(Matrix other) throws MatrixExceptions {
        checkNullException(other.data);
        checkAddAndSubstractSizeException(r, c, other.r, other.c);
        Matrix temp = new Matrix(r, c);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                temp.data[i][j] = data[i][j] - other.data[i][j];
            }
        }
        return temp;
    }

    public Matrix multiply(Matrix other) throws MatrixExceptions {
        checkNullException(other.data);
        checkMultiplySizeException(c, other.r);
        int k = other.c;
        Matrix temp = new Matrix(r, k);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < k; j++) {
                for (int p = 0; p < c; p++) {
                    temp.data[i][j] += (data[i][p] * other.data[p][j]);
                }
            }
        }
        return temp;
    }

    public Matrix scale(int x) throws MatrixExceptions {
        Matrix temp = new Matrix(c, r);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                temp.data[i][j] = data[i][j] * x;
            }
        }
        return temp;
    }

    public Matrix transpose() {
        Matrix temp = new Matrix(c, r);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                temp.data[j][i] = data[i][j];
            }
        }
        return temp;
    }

    public double get(int x, int y) throws MatrixExceptions {
        checkRangeException(x, y);
        return data[x][y];
    }

    public void set(int x, int y, int z) throws MatrixExceptions {
        checkRangeException(x, y);
        data[x][y] = z;
    }

    private double[][] read(Scanner scanner) throws IOException {
        st = new StringTokenizer(scanner.nextLine());
        if (st == null || st.countTokens() != 2) {
            throw new IOException("Input data is incorrect");
        }
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        checkNegationSize(r, c);
        double[][] tmp = new double[r][c];
        for (int i = 0; i < r; i++) {
            if (!scanner.hasNextLine()) {
                throw new IOException();
            }
            st = new StringTokenizer(scanner.nextLine());
            if (st == null || st.countTokens() != c) {
                throw new IOException("Not enough data in " + (i + 1) + " line");
            }
            for (int j = 0; j < c; j++) {
                try {
                    tmp[i][j] = Double.parseDouble(st.nextToken());
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Input data in the " + (i + 1) + " line on the " + (j + 1) + " position is incorrect");
                }
            }
        }
        return tmp;
    }

    // Exceptions -------------

    private void checkArraySizeException(double[][] x) throws SizeMismatchMatrixException {
        for (int i = 1; i < x.length; i++) {
            if (x[i].length != x[0].length) {
                throw new SizeMismatchMatrixException(SizeMismatchMatrixException.CAUSE_WRONG_SIZE);
            }
        }
    }

    private void checkAddAndSubstractSizeException(int width1, int height1, int width2, int height2) throws SizeMismatchMatrixException {
        if (width1 != width2 && height1 != height2) {
            throw new SizeMismatchMatrixException(SizeMismatchMatrixException.CAUSE_ADD_SUBTRACT);
        } else if (width1 != width2) {
            throw new SizeMismatchMatrixException(SizeMismatchMatrixException.CAUSE_ADD_SUBTRACT_WIDTH);
        } else if (height1 != height2) {
            throw new SizeMismatchMatrixException(SizeMismatchMatrixException.CAUSE_ADD_SUBTRACT_HEIGHT);
        }
    }

    private void checkMultiplySizeException(int height1, int width2) throws SizeMismatchMatrixException {
        if (height1 != width2) {
            throw new SizeMismatchMatrixException(SizeMismatchMatrixException.CAUSE_MULTIPLY);
        }
    }

    private void checkNullException(double[][] x) throws NullMatrixException {
        if (x == null) {
            throw new NullMatrixException(NullMatrixException.CAUSE);
        } else {
            for (int i = 0; i < x.length; i++) {
                if (x[i] == null)
                    throw new NullMatrixException(NullMatrixException.CAUSE);
            }
        }
    }

    private void checkRangeException(int x, int y) throws MatrixExceptions {
        if (x < 0) {
            throw new OutOfRangeMatrixException(OutOfRangeMatrixException.CAUSE_ROW_NEGATION);
        } else if (x > data.length) {
            throw new OutOfRangeMatrixException(OutOfRangeMatrixException.CAUSE_ROW_GREATER_THEN_MAX + data.length);
        } else if (y < 0) {
            throw new OutOfRangeMatrixException(OutOfRangeMatrixException.CAUSE_COLUMN_NEGATION);
        } else if (y > data[0].length) {
            throw new OutOfRangeMatrixException(OutOfRangeMatrixException.CAUSE_COLUMN_GREATER_THEN_MAX);
        }
    }

    private void checkNegationSize(int x, int y) throws SizeMismatchMatrixException {
        if (x <= 0 && y <= 0) {
            throw new SizeMismatchMatrixException(SizeMismatchMatrixException.CAUSE_NON_POSITIVE_SIZE);
        } else if (x <= 0) {
            throw new SizeMismatchMatrixException(SizeMismatchMatrixException.CAUSE_NON_POSITIVE_WIDTH);
        } else if (y <= 0) {
            throw new SizeMismatchMatrixException(SizeMismatchMatrixException.CAUSE_NON_POSITIVE_HEIGHT);
        }
    }
}
