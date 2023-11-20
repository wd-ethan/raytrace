package scene;

import scene.primitives.Light;
import scene.primitives.Sphere;

import java.awt.*;
import java.util.Collection;

public class Scene {

    public Scene(
            final Collection<Sphere> spheres,
            final Collection<Light> lights,
            final Color backgound,
            final Color ambient,
            final String output) {
        mSpheres = spheres;
        mLights = lights;
        mBackground = backgound;
        mAmbient = ambient;
        mOutput = output;
    }

    private final Collection<Sphere> mSpheres;
    private final Collection<Light> mLights;
    private final Color mBackground;
    private final Color mAmbient;
    private final String mOutput;
}
