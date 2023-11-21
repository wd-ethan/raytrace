package raytrace.scene.primitives;

import Jama.Matrix;

public class Ray {

    public Ray(final Matrix eye, final Matrix vec) {
        mEye = eye;
        mVec = vec;
    }

    private final Matrix mEye;
    private final Matrix mVec;

    public Ray transform(final Matrix transformation) {
        final Matrix parametric = transformation.times(mVec);
        final Matrix eye = transformation.times(mEye);

        return new Ray(eye, parametric);
    }

    public float vecDeterminent() {
        return (float) mVec.norm1();
    }

    public float c() {
        return (float) (mEye.get(0, 0) * mEye.get(0, 0)
                + mEye.get(1, 0) * mEye.get(1, 0)
                + mEye.get(2, 0) * mEye.get(2, 0)) - 1;
    }

    public float a() {
        final double mag =
                mVec.get(0, 0) * mVec.get(0, 0)
                + mVec.get(1, 0) * mVec.get(1, 0)
                + mVec.get(2, 0) * mVec.get(2, 0);

        return (float) (mag);
    }

    public float b() {
        return (float) (mEye.get(0, 0) * mVec.get(0, 0)
                        + mEye.get(1, 0) * mVec.get(1, 0)
                        + mEye.get(2, 0) * mVec.get(2, 0));
    }
}
