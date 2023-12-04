package com.tracer.scene.primitives;

import Jama.Matrix;
import com.tracer.primitives.Ray;
import com.tracer.scene.util.MyUtils;

/**
 * Produces light rays.
 */
public class Light {

    public Light(final String name, final Vector position, final Vector intensity){
        mName = name;
        mPosition = position;
        mIntensity = intensity;
    }

    private final String mName;
    private final Vector mPosition;
    private final Vector mIntensity;

    /**
     * Given a point in a {@link com.tracer.scene.Scene} calculates the shadow ray of this light.
     *
     * @param point the originating point.
     * @return a shadow {@link Ray}.
     */
    public Ray shadowRay(final Matrix point) {
        final Matrix position = new Matrix(new double [][] {
                {mPosition.x()},
                {mPosition.y()},
                {mPosition.z()},
                {1}
        });

        final Matrix vector = MyUtils.normalize(position.minus(point));

        return new Ray(point, vector, 1);
    }

    public Intersection intersect(final Ray shadow) {
        final Matrix position = new Matrix(new double [][] {
                {mPosition.x()},
                {mPosition.y()},
                {mPosition.z()}
        });

        final double t = shadow.solve(position);

        return new Intersection(null, null, null, t);
    }

    public Vector intensity() {
        return mIntensity;
    }
}
