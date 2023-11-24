package raytrace.scene.objects;

import Jama.Matrix;
import raytrace.scene.primitives.Ray;
import raytrace.scene.primitives.Vector;
import raytrace.scene.util.MyUtils;

import java.awt.*;

public abstract class AbstractSceneObject implements ISceneObject {

    protected AbstractSceneObject(final String name, final Color color, final Vector coefficients, final float specular) {
        mName = name;
        mColor = color;
        mCoefficients = coefficients;
        mSpecular = specular;
    }

    private final String mName;
    private final Color mColor;
    private final Vector mCoefficients;
    private final float mSpecular;

    public Vector specularColour(final Ray viewing, final Ray shadow, final Matrix point, final double intensity) {
        final Matrix sum = viewing.plus(shadow);
        final double magnitude = MyUtils.mag(sum);

        final Matrix h = sum.times(1 / magnitude);

        final double i = intensity * mCoefficients.z() * Math.pow(MyUtils.dot(h, normal(point)), mSpecular);

        return new Vector(i, i, i);
    }

    public Vector ambientColour() {
        return new Vector(mColor).times(mCoefficients.x());
    }

    public Vector diffuseColour(final Ray light, final Matrix point, final Vector intensity) {
        final Matrix lightM = light.asVector();
        final Matrix normal = normal(point);
        final double dot  = MyUtils.dot(lightM, normal);

        final double k = dot / MyUtils.mag(lightM.plus(normal));

        final Vector color = new Vector(mColor).times(intensity);

        return color.times(k).times(mCoefficients.y());
    }
}
