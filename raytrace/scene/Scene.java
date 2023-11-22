package raytrace.scene;

import raytrace.scene.objects.ISceneObject;
import raytrace.scene.primitives.Intersection;
import raytrace.scene.primitives.Light;
import raytrace.scene.primitives.Ray;

import java.awt.*;
import java.util.Collection;

public class Scene {

    public Scene(
            final Collection<ISceneObject> objects,
            final Collection<Light> lights,
            final Color background,
            final Color ambient,
            final String output) {
        mObjects = objects;
        mLights = lights;
        mBackground = background;
        mAmbient = ambient;
        mOutput = output;
    }

    private final Collection<ISceneObject> mObjects;
    private final Collection<Light> mLights;
    private final Color mBackground;
    private final Color mAmbient;
    private final String mOutput;

    public Color trace(final Ray ray) {
        Intersection intersection = new Intersection(-1, new Color(0, 0, 0));

        for (final ISceneObject object: mObjects) {
            Intersection test = object.intersect(ray);
            intersection = test.isIntersection() && test.compareTo(intersection) > 0
                    ? test
                    : intersection;
        }

        return intersection.color();
    }
}
