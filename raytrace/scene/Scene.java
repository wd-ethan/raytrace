package raytrace.scene;

import raytrace.scene.primitives.Intersection;
import raytrace.scene.primitives.Light;
import raytrace.scene.primitives.Ray;
import raytrace.scene.primitives.Sphere;

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

    public Color trace(final Ray ray) {
        Intersection intersection = new Intersection(-1, new Color(0, 0, 0));

        for (final Sphere sphere: mSpheres) {
            Intersection test = sphere.intersect(ray);
            intersection = test.isIntersection() && test.compareTo(intersection) > 0 ? test : intersection;
        }

        return intersection.color();
    }
}
