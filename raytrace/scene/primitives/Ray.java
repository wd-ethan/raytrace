package raytrace.scene.primitives;

import Jama.Matrix;
import raytrace.scene.util.MyUtils;

public class Ray {

    public static Ray DEFAULT = new Ray(new Matrix(new double[][] {{0}}), new Matrix(new double[][] {{0}}));

    public Ray(final Matrix eye, final Matrix vector, final int depth) {
        mPoint = eye;
        mVector = vector;
        mDepth = depth;
    }

    public Ray(final Matrix eye, final Matrix vector) {
        this(eye, vector, 3);
    }

    private final Matrix mPoint;
    private final Matrix mVector;
    private final int mDepth;

    public Ray transform(final Matrix transformation) {
        final Matrix parametric = transformation.times(mVector);
        final Matrix eye = transformation.times(mPoint);

        return new Ray(eye, parametric);
    }

    public Matrix at(final double t) {
        return mPoint.plus(mVector.times(t));
    }

    public double angle(final Matrix vector) {
        final double dot = MyUtils.dot(vector, mVector);
        final double a = MyUtils.mag(vector);
        final double b = MyUtils.mag(mVector);

        return dot / (a * b);
    }

    public double pDot() {
        return mPoint.get(0, 0) * mPoint.get(0, 0)
                + mPoint.get(1, 0) * mPoint.get(1, 0)
                + mPoint.get(2, 0) * mPoint.get(2, 0);
    }

    public double vDot() {
        return mVector.get(0, 0) * mVector.get(0, 0)
                + mVector.get(1, 0) * mVector.get(1, 0)
                + mVector.get(2, 0) * mVector.get(2, 0);
    }

    public double pDotV() {
        return mPoint.get(0, 0) * mVector.get(0, 0)
                + mPoint.get(1, 0) * mVector.get(1, 0)
                + mPoint.get(2, 0) * mVector.get(2, 0);
    }
}
