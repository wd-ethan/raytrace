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
        final double[][] translater = {
                {1, 0, 0, mPosition.x()},
                {0, 1, 0, mPosition.y()},
                {0, 0, 1, mPosition.z()},
                {0, 0, 0, 1}
        };
        final double[][] scaler = {
                {mScale.x(), 0, 0, 0},
                {0, mScale.y(), 0, 0},
                {0, 0, mScale.z(), 0},
                {0, 0, 0, 1}
        };

        final Matrix modelMatrix = new Matrix(translater).times(new Matrix(scaler));
        final Matrix inverse = modelMatrix.inverse();

        final Ray inverseRay = ray.transform(inverse);

        // solve for t
        final float t = quadratic(inverseRay.a(), inverseRay.b(), inverseRay.c());

        return new Intersection(t, mColor);
    }

    private float quadratic(final float a, final float b, final float c) {
        final float soln = b * b - a * c;
        float t1;
        float t2;

        if (soln >= 0) {
            t1 = (float) ((-b  + Math.sqrt(soln)) / a);
            t2 = (float) ((-b  - Math.sqrt(soln)) / a);
        }
        else {
            return -1;
        }

        return t1;
    }
}
