package raytrace.scene.primitives;

import Jama.Matrix;
import raytrace.scene.util.MyUtils;

public class Light {

    public Light(final String name, final Vector position, final Vector intensity){
        mName = name;
        mPosition = position;
        mIntensity = intensity;
    }

    private final String mName;
    private final Vector mPosition;
    private final Vector mIntensity;

    public Ray shadowRay(final Matrix point) {
        final Matrix position = new Matrix(new double [][] {
                {mPosition.x()},
                {mPosition.y()},
                {mPosition.z()},
                {1}
        });

        final Matrix vector = MyUtils.normalize(position.minus(point));

        return new Ray(point, vector);
    }

    public Vector intensity() {
        return mIntensity;
    }
}
