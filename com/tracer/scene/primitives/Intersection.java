package com.tracer.scene.primitives;

import Jama.Matrix;
import com.tracer.primitives.Ray;
import com.tracer.scene.objects.ISceneObject;
import com.tracer.scene.util.MyUtils;

import java.awt.*;
import java.util.function.Function;

public class Intersection implements Comparable<Intersection> {

    public static double MINIMUM_T = 0.000001;
    public static double MAXIMUM_T = Double.POSITIVE_INFINITY;
    public static Intersection NONE = new Intersection(null, null , null, MAXIMUM_T);

    public static boolean isIntersection(final double t) {
        return t > MINIMUM_T && t < MAXIMUM_T;
    }

    public Intersection(final ISceneObject object, final Matrix point, final Matrix normal, final double t) {
        mObject = object;
        mPoint = point;
        mNormal = normal;
        mT = t;
    }

    private final ISceneObject mObject;
    private final Matrix mPoint;
    private final Matrix mNormal;
    private final double mT;

    public boolean isIntersection() {
        return mT > MINIMUM_T && mT < MAXIMUM_T;
    }

    public Vector ambientColour() {
        return mObject.ambientColour();
    }

    public Vector diffuseColour(final Ray light, final Vector intensity) {
        return mObject.diffuseColour(light, mNormal, intensity);
    }

    public Vector specularColour(final Ray light, final Vector intensity) {
        return mObject.specularColour(light, mNormal, mPoint, intensity);
    }

    public Vector reflectedColour(final Ray initial, final Function<Ray, Color> tracer) {
        final Matrix c = MyUtils.normalize(initial.asVector());
        final Matrix v = mNormal.times(-2 * MyUtils.dot(mNormal, c)).plus(c);

        final Ray reflected =  new Ray(point(), MyUtils.normalize(v), initial.reflectedDepth());
        return mObject.reflectedColour(new Vector(tracer.apply(reflected)));

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
