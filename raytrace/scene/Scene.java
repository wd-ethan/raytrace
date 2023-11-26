package raytrace.scene;

import Jama.Matrix;
import raytrace.scene.objects.ISceneObject;
import raytrace.scene.primitives.Intersection;
import raytrace.scene.primitives.Light;
import raytrace.scene.primitives.Ray;
import raytrace.scene.primitives.Vector;

import java.awt.*;
import java.util.Collection;

public class Scene {

    public Scene(
            final Collection<ISceneObject> objects,
            final Collection<Light> lights,
            final Color background,
            final Vector ambient) {
        mObjects = objects;
        mLights = lights;
        mBackground = background;
        mAmbient = ambient;
    }

    private final Collection<ISceneObject> mObjects;
    private final Collection<Light> mLights;
    private final Color mBackground;
    private final Vector mAmbient;

    public Color trace(final Ray ray) {
        Vector color = new Vector(0, 0, 0);

        final Intersection nearest = nearestIntersection(ray);

        if (nearest.isIntersection()) {
            color = color.add(nearest.ambientColour().times(mAmbient));

            final Matrix point = nearest.point();

            for (final Light light : mLights) {
                // Calculate shadow Ray of this light
                final Ray shadowRay = light.shadowRay(point);
                // Check if this Ray hits any other objects in the scene
                final Intersection local = nearestIntersection(shadowRay);

                if (!local.isIntersection()) {
                    // Add diffuse and specular color if not in shadow
                    color = color.add(nearest.diffuseColour(shadowRay, light.intensity()));
                    color = color.add(nearest.specularColour(shadowRay, light.intensity(), point));
                }
            }
        }
        else {
            color = new Vector(mBackground);
        }

        return color.asColor();
    }

    public Intersection nearestIntersection(final Ray ray) {
        Intersection intersection = Intersection.NONE;

        for (final ISceneObject object: mObjects) {
            Intersection test = object.intersect(ray);

            if (test.isIntersection() && test.compareTo(intersection) < 0) {
                intersection = test;
            }
        }

        return intersection;
    }
}
