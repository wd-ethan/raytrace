package com.tracer.scene.util;

import Jama.Matrix;

public class MyUtils {

    public static double dot(final Matrix matrix1, final Matrix matrix2) {
        return matrix1.get(0, 0) * matrix2.get(0, 0)
                + matrix1.get(1, 0) * matrix2.get(1, 0)
                + matrix1.get(2, 0) * matrix2.get(2, 0);
    }

    public static double mag(final Matrix matrix) {
        return Math.sqrt(
                Math.pow(matrix.get(0, 0), 2)
                + Math.pow(matrix.get(1, 0), 2)
                + Math.pow(matrix.get(2, 0), 2));
    }

    public static Matrix normalize(final Matrix matrix) {
        return matrix.times(1 / mag(matrix));
    }
}
