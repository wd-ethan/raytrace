package raytrace.scene.primitives;

import java.awt.*;

import Jama.*;

public class Sphere {

    public Sphere(
            final String name,
            final Vector position,
            final Vector scale,
            final Color color,
            final Vector constants,
            final float specular){
        mName = name;
        mPosition = position;
        mScale = scale;
        mColor = color;
        mConstants = constants;
        mSpecular = specular;
    }

    private final String mName;
    private final Vector mPosition;
    private final Vector mScale;
    private final Color mColor;
    private final Vector mConstants;
    private final float mSpecular;

    public Intersection intersect(final Ray ray) {
        final double[][] modelView = {
                {mScale.x(), 0, 0, mPosition.x()},
                {0, mScale.y(), 0, mPosition.y()},
                {0, 0, mScale.z(), mPosition.z()},
                {0, 0, 0, 1}
        };

        final Matrix modelMatrix = new Matrix(modelView);
        final Matrix inverse = modelMatrix.inverse();
        final Ray inverseRay = ray.transform(inverse);

        return intersection(inverseRay);
    }

    private Intersection intersection(final Ray ray) {
        final float a = ray.a();
        final float b = ray.b();
        final float c = ray.c();
        final float d = b * b - a * c;

        Intersection intersection = Intersection.NONE;

        if (d == 0) {
            intersection = new Intersection(-b / (2 * a), mColor);
        }
        else if (d > 0){
            final float t1 = (float) (-b + Math.sqrt(d) / a);
            final float t2 = (float) (-b - Math.sqrt(d) / a);
            final float t = t2 < t1 && t2 > 1.0001 ? t2 : t1;

            intersection = new Intersection(t, mColor);
        }

        return intersection;
    }
}
