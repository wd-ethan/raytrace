package com.tracer.primitives;

import Jama.Matrix;
import com.tracer.scene.util.MyUtils;

public class Ray {

    public Ray(final Matrix eye, final Matrix vector, final int depth) {
        mPoint = eye;
        mVector = vector;
        mDepth = depth;
    }

    public Ray(final Matrix eye, final Matrix vector) {
        this(eye, vector, 3);
    }

    public final Matrix mPoint;
    private final Matrix mVector;
    final int mDepth;

    public boolean isDead() {
        return mDepth <= 0;
    }

    public boolean isReflected() {
        return mDepth < 3;
    }

    public boolean isFromEye() {
        return mDepth == 3;
    }

    public int reflectedDepth() {
        return mDepth - 1;
    }

    public Ray transform(final Matrix transformation) {
        final Matrix parametric = transformation.times(mVector);
        final Matrix eye = transformation.times(mPoint);

        return new Ray(eye, parametric, mDepth);
    }

    public Matrix at(final double t) {
        return mPoint.plus(mVector.times(t));
    }

    public double solve(final Matrix point) {
        final Matrix vector3D = mVector.getMatrix(0, 2, 0, 0);
        final Matrix position3d = mPoint.getMatrix(0, 2, 0, 0);

        final Matrix solution = vector3D.solve(point.minus(position3d));

        return solution.get(0, 0);
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
