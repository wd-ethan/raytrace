package raytrace.scene.primitives;

import Jama.Matrix;
import raytrace.scene.objects.ISceneObject;

public class Intersection implements Comparable<Intersection> {

    public static double MINIMUM_T = 0.000001;
    public static double MAXIMUM_T = Double.POSITIVE_INFINITY;
    public static Intersection NONE = new Intersection(null, null , MAXIMUM_T);

    public static boolean isIntersection(final double t) {
        return t > MINIMUM_T && t < MAXIMUM_T;
    }

    public Intersection(final ISceneObject object, final Matrix point, final double t) {
        mObject = object;
        mPoint = point;
        mT = t;
    }

    private final ISceneObject mObject;
    private final Matrix mPoint;
    private final double mT;

    public boolean isIntersection() {
        return mT > MINIMUM_T && mT < MAXIMUM_T;
    }

    public Vector ambientColour() {
        return mObject.ambientColour();
    }

    public Vector diffuseColour(final Ray light, final Vector intensity) {
        return mObject.diffuseColour(light, mPoint, intensity);
    }

    public Vector specularColour(final Ray light, final Vector intensity, final Matrix point) {
        return mObject.specularColour(light, intensity, point);
    }

    public Matrix point() {
        return mPoint;
    }

    @Override
    public int compareTo(Intersection o) {
        assert mT > 0 && o.mT > 0;

        return (mT == o.mT) ? 0 : (mT - o.mT) < 0 ? -1 : 1;
    }
}
