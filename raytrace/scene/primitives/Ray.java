package raytrace.scene.primitives;

import Jama.Matrix;

public class Ray {

    public Ray(final Matrix eye, final Matrix vector, final int depth) {
        mEye = eye;
        mVector = vector;
        mDepth = depth;
    }

    public Ray(final Matrix eye, final Matrix vector) {
        this(eye, vector, 3);
    }

    private final Matrix mEye;
    private final Matrix mVector;
    private final int mDepth;

    public Ray transform(final Matrix transformation) {
        final Matrix parametric = transformation.times(mVector);
        final Matrix eye = transformation.times(mEye);

        return new Ray(eye, parametric);
    }

    public float c() {
        return (float) (mEye.get(0, 0) * mEye.get(0, 0)
                + mEye.get(1, 0) * mEye.get(1, 0)
                + mEye.get(2, 0) * mEye.get(2, 0)) - 1;
    }

    public float a() {
        final double mag =
                mVector.get(0, 0) * mVector.get(0, 0)
                + mVector.get(1, 0) * mVector.get(1, 0)
                + mVector.get(2, 0) * mVector.get(2, 0);

        return (float) (mag);
    }

    public float b() {
        return (float) (mEye.get(0, 0) * mVector.get(0, 0)
                        + mEye.get(1, 0) * mVector.get(1, 0)
                        + mEye.get(2, 0) * mVector.get(2, 0));
    }
}
