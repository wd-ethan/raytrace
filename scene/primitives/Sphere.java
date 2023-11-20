package scene.primitives;

import scene.primitives.Vector;
import java.awt.*;

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

    public boolean intersect(final Vector ray) {
        return false;
    }
}
