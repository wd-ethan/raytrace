package src.scene.objects;

import Jama.Matrix;
import src.scene.primitives.Ray;
import src.scene.primitives.Vector;
import src.scene.util.MyUtils;

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

    public Vector ambientColour() {
        return new Vector(mColor).times(mCoefficients.x());
    }

    public Vector diffuseColour(final Ray light, final Matrix normal, final Vector intensity) {
        final Matrix l = light.asVector();
        final double dot  = Math.max(MyUtils.dot(normal, l), 0);

        return new Vector(mColor).times(intensity).times(dot).times(mCoefficients.y());
    }

    public Vector specularColour(final Ray light, final Matrix normal, final Matrix point, final Vector intensity) {
        final Matrix l = light.asVector();
        final Matrix r = normal.times(2 * MyUtils.dot(normal, l)).minus(l);
        final Matrix v = MyUtils.normalize(point.times(-1));

        return intensity.times(mCoefficients.z()).times(Math.pow(Math.max(MyUtils.dot(r, v), 0), mSpecular)).times(255);
    }
}
