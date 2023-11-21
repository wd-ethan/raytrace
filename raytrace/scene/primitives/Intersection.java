package raytrace.scene.primitives;

import java.awt.*;

public class Intersection implements Comparable<Intersection> {

    public Intersection(final float t, final Color color) {
        mT = t;
        mColor = color;
    }

    private final float mT;
    private final Color mColor;

    public boolean isIntersection() {
        return mT > 0.0001;
    }

    public Color color() {
        return mColor;
    }

    @Override
    public int compareTo(Intersection o) {
        return (mT == o.mT) ? 0 : (mT - o.mT) < 0 ? -1 : 1;
    }
}
