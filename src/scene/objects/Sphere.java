package src.scene.objects;

import java.awt.*;

import Jama.*;
import src.scene.primitives.Intersection;
import src.scene.primitives.Ray;
import src.scene.primitives.Vector;
import src.scene.util.MyUtils;

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

        final Matrix modelMatrix = new Matrix(modelView);
        final Matrix inverse = modelMatrix.inverse();
        final Matrix inverseTranspose = modelMatrix.inverse().transpose();

        final Ray rayInSphereCoords = ray.transform(inverse);

        final double t = solve(rayInSphereCoords);

        final Matrix normalInSphere = rayInSphereCoords.at(t).minus(ORIGIN);
        final Matrix normalInWorld = inverseTranspose.times(normalInSphere);
        final Matrix normal = MyUtils.normalize(normalInWorld);

        return Intersection.isIntersection(t)
                ? new Intersection(this, ray.at(t), normal, t)
                : Intersection.NONE;
    }

    private double solve(final Ray ray) {
        final double a = ray.vDot();
        final double b = ray.pDotV();
        final double c = ray.pDot() - RADIUS;
        final double d = Math.pow(b, 2) - a * c;

        double t = -1;

        if (d == 0) {
            t = -b / a;
        }
        else if (d > 0){
            final double t1 =  -b/a + Math.sqrt(d) / a;
            final double t2 =  -b/a - Math.sqrt(d) / a;

            return Math.min(t1, t2);
        }

        return t;
    }
}
