package raytrace.scene.primitives;

import Jama.Matrix;

import java.awt.*;

public class Pixel {

    public Pixel(final int cols, final int rows, final int col, final int row) {
        mCols = cols;
        mRows = rows;

        mCol = col;
        mRow = row;
    }

    private final float mCols;
    private final float mRows;
    final float mCol;
    private final float mRow;
    private Color mColor = Color.black;

    public void setColor(final Color color) {
        mColor = color;
    }

    public String asRgb() {
        final StringBuilder stb = new StringBuilder();
        float[] rgb = new float[4];

        rgb = mColor.getRGBComponents(rgb);

        return stb.append((int) (rgb[0]*255)).append(" ").append((int) (rgb[1]*255)).append(" ").append((int) (rgb[2]*255)).toString();
    }

    public Ray intersect(final Matrix eye, final ViewPort viewPort) {
        final Matrix worldCoords = asCameraCoordinate(viewPort);

        return new Ray(eye, worldCoords);
    }

    private Matrix asCameraCoordinate(final ViewPort view) {
        final float x = view.mLeft + view.mRight * 2 * mCol / mCols;
        final float y = view.mBottom + view.mTop * 2 * mRow / mRows;
        final float z = -view.mNear;

        return new Matrix(new double[][] {{x}, {-y}, {z}, {0}});
    }
}
