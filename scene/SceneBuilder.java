package scene;

import scene.primitives.Light;
import scene.primitives.Resolution;
import scene.primitives.Sphere;
import scene.primitives.ViewPort;

import java.awt.*;
import java.util.Collection;
import java.util.HashSet;

public class SceneBuilder {

    SceneBuilder() {}

    private final Collection<Sphere> mSpheres = new HashSet<>();
    private final Collection<Light> mLights = new HashSet<>();
    private float mNear = -1;
    private float mLeft = -1;
    private float mRight = 1;
    private float mTop = 1;
    private float mBottom = -1;
    private Resolution mResolution = new Resolution();
    private Color mBackgound = new Color(255, 255, 255);
    private Color mAmbient = new Color(127, 127, 127);
    private String mOutput = "test.ppm";

    public Scene build() {
        return new Scene(new ViewPort(mNear, mTop, mBottom, mLeft, mRight),
                mResolution, mSpheres, mLights, mBackgound, mAmbient, mOutput);
    }

    public SceneBuilder withNearPlane(final float near) {
        mNear = near;

        return this;
    }

    public SceneBuilder withLeftPlane(final float left) {
        mLeft = left;

        return this;
    }

    public SceneBuilder withRightPlane(final float right) {
        mRight = right;

        return this;
    }

    public SceneBuilder withTopPlane(final float top) {
        mTop = top;

        return this;
    }

    public SceneBuilder withBottomPlane(final float bottom) {
        mBottom = bottom;

        return this;
    }

    public SceneBuilder withResolution(final Resolution resolution) {
        mResolution = resolution;

        return this;
    }

    public SceneBuilder withSphere(final Sphere sphere) {
        mSpheres.add(sphere);

        return this;
    }

    public SceneBuilder withLight(final Light light) {
        mLights.add(light);

        return this;
    }

    public SceneBuilder withBackground(final Color color) {
        mBackgound = color;

        return this;
    }

    public SceneBuilder withAmbient(final Color color) {
        mAmbient = color;

        return this;
    }

    public SceneBuilder withOutputName(final String outputName) {
        mOutput = outputName;

        return this;
    }
}
