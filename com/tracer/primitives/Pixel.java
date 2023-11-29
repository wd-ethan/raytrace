package com.tracer.primitives;

import Jama.Matrix;

import java.awt.*;

public class Pixel implements Comparable<Pixel>{

    public Pixel(final int cols, final int rows, final int col, final int row) {
        mCols = cols;
        mRows = rows;

        mCol = col;
        mRow = row;
    }

    private final int mCols;
    private final int mRows;
    final int mCol;
    private final int mRow;
    private Color mColor = Color.black;

    public void setColor(final Color color) {
        mColor = color;
    }

    public String asRgb() {
        final StringBuilder stb = new StringBuilder();
        float[] rgb = new float[4];

        rgb = mColor.getRGBComponents(rgb);

        return stb.append(
                (int) (rgb[0]*255)).append(" ").append(
                        (int) (rgb[1]*255)).append(" ").append(
                                (int) (rgb[2]*255)).toString();
    }

    public Ray intersect(final Matrix eye, final ViewPort viewPort) {
        final Matrix cameraCoordinates = asCameraCoordinates(viewPort);

        return new Ray(eye, cameraCoordinates);
    }

    private Matrix asCameraCoordinates(final ViewPort view) {
        final double x = -view.width() * ((double) mCol / mCols - 0.5);
        final double y = view.height() * ((double) mRow / mRows - 0.5);
        final float z = -view.mNear;

        return new Matrix(new double[][] {{x}, {y}, {z}, {0}});
    }

    @Override
    public int compareTo(Pixel o) {
        final int rowDiff = o.mRow - mRow;
        final int colDiff = o.mCol - mCol;

        return rowDiff == 0 ? colDiff : rowDiff;
    }
}
