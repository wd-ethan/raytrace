package scene;

import scene.primitives.Light;
import scene.primitives.Resolution;
import scene.primitives.Sphere;
import scene.primitives.ViewPort;

import java.awt.*;
import java.util.Collection;

public class Scene {

    public Scene(
            final ViewPort viewPort,
            final Resolution resolution,
            final Collection<Sphere> spheres,
            final Collection<Light> lights,
            final Color backgound,
            final Color ambient,
            final String output) {
        mViewPort = viewPort;
        mResolution = resolution;
        mSpheres = spheres;
        mLights = lights;
        mBackground = backgound;
        mAmbient = ambient;
        mOutput = output;
    }

    private final ViewPort mViewPort;
    private final Resolution mResolution;
    private final Collection<Sphere> mSpheres;
    private final Collection<Light> mLights;
    private final Color mBackground;
    private final Color mAmbient;
    private final String mOutput;
}
