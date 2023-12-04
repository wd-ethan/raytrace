package com.tracer.scene.objects;

import java.awt.*;

import Jama.*;
import com.tracer.primitives.ViewPort;
import com.tracer.scene.primitives.Intersection;
import com.tracer.primitives.Ray;
import com.tracer.scene.primitives.Vector;
import com.tracer.scene.util.MyUtils;

/**
 * A spherical object.
 */
public class Sphere extends AbstractSceneObject {

    private static final Matrix ORIGIN = new Matrix(new double[][] {{0}, {0}, {0}, {1}});
    private static final int RADIUS = 1;

    public Sphere(
            final String name,
            final Vector position,
            final Vector scale,
            final Color color,
            final Vector coefficients,
            final float specular){
        super(name, color, coefficients, specular);

        mPosition = position;
        mScale = scale;
    }

    private final Vector mPosition;
    private final Vector mScale;

    public Intersection intersect(final Ray ray) {
        final double[][] modelView = {
                {mScale.x(), 0, 0, mPosition.x()},
                {0, mScale.y(), 0, mPosition.y()},
                {0, 0, mScale.z(), mPosition.z()},
                {0, 0, 0, 1}
        };

        // Get into sphere coordinates
        final Matrix modelMatrix = new Matrix(modelView);
        final Matrix inverse = modelMatrix.inverse();
        final Matrix inverseTranspose = inverse.transpose();

        final Ray rayInSphereCoords = ray.transform(inverse);

        // Calculate intersections
        final double min = ray.isReflected() ? Intersection.MINIMUM_T : ViewPort.minDepth(ray);
        final double t = solve(rayInSphereCoords, min);
        final double t2 = solve(rayInSphereCoords, Intersection.MINIMUM_T);

        // Calculate normal
        final Matrix normalInSphere = rayInSphereCoords.at(t).minus(ORIGIN);
        final Matrix normalInWorld = inverseTranspose.times(normalInSphere);
        Matrix normal = MyUtils.normalize(new Matrix(new double[][] {
                {normalInWorld.get(0, 0)},
                {normalInWorld.get(1, 0)},
                {normalInWorld.get(2, 0)},
                {0}
        }));

        // We are on the inside of the sphere
        if (t != t2) {
            normal = normal.times(-1);
        }

        return Intersection.isIntersection(t)
                ? new Intersection(this, ray.at(t), normal, t)
                : Intersection.NONE;
    }

    private double solve(final Ray ray, final double min) {
        final double a = ray.vDot();
        final double b = ray.pDotV();
        final double c = ray.pDot() - RADIUS;
        final double d = Math.pow(b, 2) - a * c;

        double t = Double.POSITIVE_INFINITY;

        if (d == 0) {
            t = - b / a;
            return t > min ? t : Double.POSITIVE_INFINITY;
        }
        else if (d > 0){
            double t1 =  -b/a + Math.sqrt(d) / a;
            double t2 =  -b/a - Math.sqrt(d) / a;

            t1 = t1 > min ? t1 : Double.POSITIVE_INFINITY;
            t2 = t2 > min ? t2 : Double.POSITIVE_INFINITY;

            return Math.min(t1, t2);
        }

        return t;
    }
}
