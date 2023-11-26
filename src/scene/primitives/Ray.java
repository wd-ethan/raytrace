package src.scene.primitives;

import Jama.Matrix;
import src.scene.util.MyUtils;

public class Ray {

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

    public Matrix asVector() {
        return mVector;
    }

    public double pDot() {
        return MyUtils.dot(mPoint, mPoint);
    }

    public double vDot() {
        return MyUtils.dot(mVector, mVector);
    }

    public double pDotV() {
        return MyUtils.dot(mVector, mPoint);
    }
}
