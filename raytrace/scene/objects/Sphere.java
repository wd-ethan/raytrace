package raytrace.scene.objects;

import java.awt.*;

import Jama.*;
import raytrace.scene.primitives.Intersection;
import raytrace.scene.primitives.Ray;
import raytrace.scene.primitives.Vector;

public class Sphere extends AbstractSceneObject {

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

    public Matrix normal(final Matrix point) {
        return point.minus(mPosition.asMatrix());
    }

    public Intersection intersect(final Ray ray) {
        final double[][] modelView = {
                {mScale.x(), 0, 0, mPosition.x()},
                {0, mScale.y(), 0, mPosition.y()},
                {0, 0, mScale.z(), mPosition.z()},
                {0, 0, 0, 1}
        };

        final Matrix modelMatrix = new Matrix(modelView);
        final Matrix inverse = modelMatrix.inverse();
        final Ray rayInSphereCoords = ray.transform(inverse);

        final double t = solve(rayInSphereCoords);

        return Intersection.isIntersection(t)
                ? new Intersection(this, ray.at(t), t)
                : Intersection.NONE;
    }

    private double solve(final Ray ray) {
        final double a = ray.vDot();
        final double b = ray.pDotV();
        final double c = ray.pDot() - 1;
        final double d = Math.pow(b, 2) - a * c;

        double t = -1;

        if (d == 0) {
            t = -b / (2 * a);
        }
        else if (d > 0){
            final double t1 =  -b / a + Math.sqrt(d) / a;
            final double t2 =  -b / a - Math.sqrt(d) / a;

            t = t2 < t1 && t2 > Intersection.MINIMUM_T ? t2 : t1;
        }

        return t;
    }
}
