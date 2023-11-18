package scene;

import java.awt.*;
import java.util.Collection;
import java.util.HashSet;

public class SceneBuilder {

    SceneBuilder() {}

    private final Collection<Sphere> mSpheres = new HashSet<>();
    private final Collection<Light> mLights = new HashSet<>();
    private ViewPort mViewPort = new ViewPort();
    private Color mBackgound = new Color(255, 255, 255);
    private Color mAmbient = new Color(127, 127, 127);
    private String mOutput = "test.ppm";

    public Scene build() {
        return new Scene(mViewPort, mSpheres, mLights, mBackgound, mAmbient, mOutput);
    }

    public SceneBuilder withViewPort(final ViewPort viewPort) {
        mViewPort = viewPort;

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
