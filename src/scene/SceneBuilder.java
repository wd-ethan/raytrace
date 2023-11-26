package src.scene;

import src.scene.objects.ISceneObject;
import src.scene.primitives.Light;
import src.scene.primitives.Resolution;
import src.scene.objects.Sphere;
import src.scene.primitives.Vector;
import src.scene.primitives.ViewPort;
import src.tracer.OutputFile;
import src.tracer.RayTracer;

import java.awt.*;
import java.util.Collection;
import java.util.HashSet;

public class SceneBuilder {

    SceneBuilder() {}

    private final Collection<ISceneObject> mObjects = new HashSet<>();
    private final Collection<Light> mLights = new HashSet<>();
    private float mNear = -1;
    private float mLeft = -1;
    private float mRight = 1;
    private float mTop = 1;
    private float mBottom = -1;
    private Resolution mResolution = new Resolution();
    private Color mBackground = new Color(255, 255, 255);
    private Vector mAmbient = new Vector(0, 0, 0);
    private String mOutput = "test.ppm";

    public Scene buildScene() {
        return new Scene(mObjects, mLights, mBackground, mAmbient);
    }

    public RayTracer buildRayTracer() {
        return new RayTracer(mResolution, new ViewPort(mNear, mTop, mBottom, mLeft, mRight));
    }

    public OutputFile buildOutputFile() {
        return new OutputFile(mOutput);
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
        mObjects.add(sphere);

        return this;
    }

    public SceneBuilder withLight(final Light light) {
        mLights.add(light);

        return this;
    }

    public SceneBuilder withBackground(final Color color) {
        mBackground = color;

        return this;
    }

    public SceneBuilder withAmbient(final Vector color) {
        mAmbient = color;

        return this;
    }

    public SceneBuilder withOutputName(final String outputName) {
        mOutput = outputName;

        return this;
    }
}
