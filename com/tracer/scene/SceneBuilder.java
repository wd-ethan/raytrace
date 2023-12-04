package com.tracer.scene;

import com.tracer.scene.objects.ISceneObject;
import com.tracer.scene.primitives.Light;
import com.tracer.primitives.Resolution;
import com.tracer.scene.objects.Sphere;
import com.tracer.scene.primitives.Vector;
import com.tracer.primitives.ViewPort;
import com.tracer.primitives.OutputFile;
import com.tracer.RayTracer;

import java.awt.*;
import java.util.Collection;
import java.util.HashSet;

/**
 * Builder class which is responsible for constructing objects needed by a {@link RayTracer}.
 */
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

    /**
     * Builds a {@link Scene}.
     *
     * @return a constructed {@link Scene}.
     */
    public Scene buildScene() {
        return new Scene(mObjects, mLights, new ViewPort(mNear, mTop, mBottom, mLeft, mRight), mBackground, mAmbient);
    }

    /**
     * Builds a {@link RayTracer}.
     *
     * @return a constructed {@link RayTracer}.
     */
    public RayTracer buildRayTracer() {
        return new RayTracer(mResolution, new ViewPort(mNear, mTop, mBottom, mLeft, mRight));
    }

    /**
     * build a {@link OutputFile}.
     *
     * @return a constructed {@link OutputFile}.
     */
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
