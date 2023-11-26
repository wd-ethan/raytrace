package raytrace.scene.primitives;

import Jama.Matrix;

import java.awt.*;

public class Vector {

    public Vector(final double x, final double y, final double z, final double a) {
        mX = x;
        mY = y;
        mZ = z;
        mA = a;
    }

    public Vector(final double x, final double y, final double z) {
        mX = x;
        mY = y;
        mZ = z;
        mA = 0;
    }

    public Vector(final Matrix vector) {
        mX = vector.get(0, 0);
        mY = vector.get(1, 0);
        mZ = vector.get(2, 0);
        mA = vector.get(3, 0);
    }

    public Vector(final Color color) {
        mX = color.getRed();
        mY = color.getGreen();
        mZ = color.getBlue();
        mA = 0;
    }

    public Vector() {
        this(new Matrix(new double[][] {
                {0},
                {0},
                {0},
                {0}
        }));
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

    public Vector add(final Vector vector) {
        return new Vector(x() + vector.x(), y() + vector.y(), z() + vector.z());
    }

    public Vector times(final Vector vector) {
        return new Vector(x() * vector.x(), y() * vector.y(), z() * vector.z());
    }

    public Vector times(final double k) {
        return new Vector(k * x(), k * y(), k * z());
    }

    public Color asColor() {
        return new Color(
                clamp(x()),
                clamp(y()),
                clamp(z()));
    }

    public Matrix asMatrix() {
        return new Matrix(new double[][] {
                {mX},
                {mY},
                {mZ},
                {mA}
        });
    }

    private int clamp(final double i) {
        return i > 255 ? 255 : i < 0 ? 0 : (int) i;
    }
}
