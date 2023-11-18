package scene;

import java.awt.*;
import java.util.Collection;
import java.util.List;

public class Scene {

    public Scene(
            final ViewPort viewPort,
            final Collection<Sphere> spheres,
            final Collection<Light> lights,
            final Color backgound,
            final Color ambient,
            final String output) {
        mViewPort = viewPort;
        mSpheres = spheres;
        mLights = lights;
        mBackground = backgound;
        mAmbient = ambient;
        mOutput = output;
    }

    private final ViewPort mViewPort;
    private final Collection<Sphere> mSpheres;
    private final Collection<Light> mLights;
    private final Color mBackground;
    private final Color mAmbient;
    private final String mOutput;
}
