package src.main.java.com.tracer.scene;

import src.main.java.com.tracer.scene.objects.ISceneObject;
import src.main.java.com.tracer.scene.primitives.Intersection;
import src.main.java.com.tracer.scene.primitives.Light;
import src.main.java.com.tracer.scene.primitives.Ray;
import src.main.java.com.tracer.scene.primitives.Vector;

import java.awt.*;
import java.util.Collection;

public class Scene {

    private static final Color BLACK = Color.black;
    private static final Vector NONE = new Vector(BLACK);

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
        final Vector color = ray.isDead() ? NONE : intersect(ray);

        return color.asColor();
    }

    private Vector intersect(final Ray ray) {
        final Intersection nearest = nearestIntersection(ray);

        return nearest.isIntersection() ? sumColour(ray, nearest) : miss(ray);
    }

    private Vector sumColour(final Ray ray, final Intersection intersection) {
        Vector color = NONE;

        color = color.add(intersection.ambientColour().times(mAmbient));
        color = color.add(localIllumination(intersection));
        color = color.add(intersection.reflectedColour(ray, this::trace));

        return color;
    }

    private Vector miss(final Ray ray) {
        return ray.isReflected() ? NONE : new Vector(mBackground);
    }

    private Intersection nearestIntersection(final Ray ray) {
        Intersection intersection = Intersection.NONE;

        for (final ISceneObject object: mObjects) {
            Intersection test = object.intersect(ray);

            // Find the nearest intersection
            if (test.isIntersection() && test.compareTo(intersection) < 0) {
                intersection = test;
            }
        }

        return intersection;
    }

    private Vector localIllumination(final Intersection local) {
        Vector color = NONE;

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
