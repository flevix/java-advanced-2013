package ru.ifmo.ctddev.nechaev.task1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java [package_name].Main [input_file] [output_file]");
            System.exit(1);
        }
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            Matrix m1 = new Matrix(scanner);
            Matrix m2 = new Matrix(scanner);
            Matrix m3 = new Matrix(scanner);
            Matrix m4 = m1.multiply(m1).add(m2.multiply(m3));
            m4.write(new File(args[1]));
        }
        System.exit(0);
        double[][] first = {
                {1, 2},
                {4, 5},
                {6, 7}
        };
        double[][] second = {
                {5, 6, 7},
                {7, 8, 9},
                {9, 10, 11}
        };
        Matrix c;
        Matrix a = new Matrix(first);
        Matrix b = new Matrix(second);
        try {
            c = a.multiply(a.transpose()).add(b);
            printMatrix(c);
        } catch (MatrixExceptions e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        try {
            PrintWriter out = new PrintWriter(new File(args[1]));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void printMatrix(Matrix matrix) {
        try {
            for (int i = 0; i < matrix.r; i++) {
                for (int j = 0; j < matrix.c; j++) {
                    System.out.print(matrix.get(i, j) + " ");
                }
                System.out.println();
            }
            System.out.println("----");
        } catch (MatrixExceptions e) {
            System.err.println(e);
        }
    }
}
