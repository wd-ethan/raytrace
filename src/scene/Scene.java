package src.scene;

import Jama.Matrix;
import src.scene.objects.ISceneObject;
import src.scene.primitives.Intersection;
import src.scene.primitives.Light;
import src.scene.primitives.Ray;
import src.scene.primitives.Vector;

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
            color = color.add(localIllumination(nearest));
            // color = color.add(reflected)
        }
        else {
            color = new Vector(mBackground);
        }

        return color.asColor();
    }

    private Intersection nearestIntersection(final Ray ray) {
        Intersection intersection = Intersection.NONE;

        for (final ISceneObject object: mObjects) {
            Intersection test = object.intersect(ray);

            if (test.isIntersection() && test.compareTo(intersection) < 0) {
                intersection = test;
            }
        }

        return intersection;
    }

    private Vector localIllumination(final Intersection local) {
        Vector color = new Vector();

        for (final Light light : mLights) {
            // Calculate shadow Ray of this light
            final Ray shadowRay = light.shadowRay(local.point());
            // Check if this Ray hits any other objects in the scene
            final Intersection shadow = nearestIntersection(shadowRay);

            if (!shadow.isIntersection()) {
                // Add diffuse and specular color if not in shadow
                color = color.add(local.diffuseColour(shadowRay, light.intensity()));
                color = color.add(local.specularColour(shadowRay, light.intensity()));
            }
        }

        return color;
    }
}
