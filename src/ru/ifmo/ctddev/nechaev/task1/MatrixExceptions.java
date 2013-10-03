package ru.ifmo.ctddev.nechaev.task1;

/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 21.02.13
 * Time: 11:16
 */

public class MatrixExceptions extends RuntimeException {
    private String detail;

    public MatrixExceptions(String message) {
        this.detail = message;
    }

    @Override
    public String toString() {
        return this.getClass().getCanonicalName() + " : " + detail;
    }
}

class OutOfRangeMatrixException extends MatrixExceptions {
    public static final String CAUSE_ROW_NEGATION = "The row must be non negative.";
    public static final String CAUSE_ROW_GREATER_THEN_MAX = "The row must be less then ";
    public static final String CAUSE_COLUMN_NEGATION = "The column must be non negative.";
    public static final String CAUSE_COLUMN_GREATER_THEN_MAX = "The column must be less then ";

    public OutOfRangeMatrixException(String message) {
        super(message);
    }
}

class NullMatrixException extends MatrixExceptions {
    public static final String CAUSE = "The Matrix is NULL";

    public NullMatrixException(String message) {
        super(message);
    }
}

class SizeMismatchMatrixException extends MatrixExceptions {
    public static final String CAUSE_ADD_SUBTRACT = "+/- The width and height of the first matrix should coincide with the sizes of the second.";
    public static final String CAUSE_ADD_SUBTRACT_WIDTH = "+/- The width of the first matrix must correspond to the width of the second.";
    public static final String CAUSE_ADD_SUBTRACT_HEIGHT = "+/- The height of the first matrix must correspond to the height of the second.";
    public static final String CAUSE_MULTIPLY = "* Height first matrix not equal weight second matrix.";
    public static final String CAUSE_WRONG_SIZE = "The array is not rectangle.";
    public static final String CAUSE_NON_POSITIVE_SIZE = "The size must be a positive.";
    public static final String CAUSE_NON_POSITIVE_WIDTH = "Width is a positive number.";
    public static final String CAUSE_NON_POSITIVE_HEIGHT = "Height is a positive number.";

    public SizeMismatchMatrixException(String message) {
        super(message);
    }
}