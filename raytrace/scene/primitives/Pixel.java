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

        return stb.append(
                (int) (rgb[0]*255)).append(" ").append(
                        (int) (rgb[1]*255)).append(" ").append(
                                (int) (rgb[2]*255)).toString();
    }

    public Ray intersect(final Matrix eye, final ViewPort viewPort) {
        final Matrix cameraCoordinates = inCameraCoordinates(viewPort);

        return new Ray(eye, cameraCoordinates);
    }

    private Matrix inCameraCoordinates(final ViewPort view) {
        final float x = view.width() * (2 * mCol / mCols - 1);
        final float y = view.height() * (2 * mRow / mRows - 1);
        final float z = -view.mNear;

        return new Matrix(new double[][] {{x}, {y}, {z}, {0}});
    }
}
