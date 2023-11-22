package raytrace.scene.primitives;

public class Vector {

    // TODO : remove Vector
    public Vector(final float x, final float y, final float z, final float a) {
        mX = x;
        mY = y;
        mZ = z;
        mA = a;
    }

    private final float mX;
    private final float mY;
    private final float mZ;
    private final float mA;

    public float x() {
        return mX;
    }

    public float y() {
        return mY;
    }

    public float z() {
        return mZ;
    }
}
