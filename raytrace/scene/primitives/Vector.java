package raytrace.scene.primitives;

import Jama.Matrix;

public class Vector {

    public Vector(final Matrix vector) {
        mX = vector.get(0, 0);
        mY = vector.get(1, 0);
        mZ = vector.get(2, 0);
        mA = vector.get(3, 0);
    }

    private final double mX;
    private final double mY;
    private final double mZ;
    private final double mA;

    public double x() {
        return mX;
    }

    public double y() {
        return mY;
    }

    public double z() {
        return mZ;
    }
}
