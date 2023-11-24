package raytrace.scene.primitives;

import Jama.Matrix;

import java.awt.*;

public class Intersection implements Comparable<Intersection> {

    public static double MINIMUM_T = 0.00001;
    public static Intersection NONE = new Intersection(Ray.DEFAULT, -1, Color.black, new Vector(), new Matrix(new double[][] {{}}));

    public static boolean isIntersection(final double t) {
        return t > 0.00001;
    }

    public Intersection(final Ray ray, final double t, final Color color, final Vector constants, final Matrix normal) {
        mRay = ray;
        mT = t;
        mColor = color;
        mConstants = constants;
        mNormal = normal;
    }

    private final Ray mRay;
    private final double mT;
    private final Color mColor;
    private final Vector mConstants;
    private final Matrix mNormal;

    public boolean isIntersection() {
        return mT > MINIMUM_T;
    }

    private Vector color() {
        return new Vector(mColor.getRed(), mColor.getGreen(), mColor.getBlue());
    }

    public Vector ambientColour() {
        final double ka = mConstants.x();

        return color().times(ka);
    }

    public Vector diffuseColour() {
        final double kd = mConstants.y();

        return color().times(kd);
    }

    public Matrix point() {
        return mRay.at(mT);
    }

    public Matrix normal() {
        return mNormal;
    }

    @Override
    public int compareTo(Intersection o) {
        return (mT == o.mT) ? 0 : (mT - o.mT) < 0 ? -1 : 1;
    }
}
