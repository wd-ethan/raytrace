package com.tracer.scene;

import com.tracer.primitives.ViewPort;
import com.tracer.scene.objects.ISceneObject;
import com.tracer.scene.primitives.Intersection;
import com.tracer.scene.primitives.Light;
import com.tracer.primitives.Ray;
import com.tracer.scene.primitives.Vector;

import java.awt.*;
import java.util.Collection;

/**
 * A {@link Scene} is a collection of objects, lighting, and a background.
 */
public class Scene {

    private static final Color BLACK = Color.black;
    private static final Vector NONE = new Vector(BLACK);

    public Scene(
            final Collection<ISceneObject> objects,
            final Collection<Light> lights,
            final ViewPort view,
            final Color background,
            final Vector ambient) {
        mObjects = objects;
        mLights = lights;
        mView = view;
        mBackground = background;
        mAmbient = ambient;
    }

    private final Collection<ISceneObject> mObjects;
    private final Collection<Light> mLights;
    private final ViewPort mView;
    private final Color mBackground;
    private final Vector mAmbient;

    /**
     * Traces a given {@link Ray} through this {@link Scene}.
     *
     * @param ray the {@link Ray} to trace.
     * @return the {@link Color} that the pixel this ray passes through should be.
     */
    public Color trace(final Ray ray) {
        final Vector color = ray.isDead() ? NONE : intersect(ray);

        return color.asColor();
    }

    /**
     * Checks the given {@link Ray} for intersections in this {@link Scene}.
     *
     * @param ray the {@link Ray} to intersect.
     * @return the colour this produced by intersection (or lack of one),
     */
    private Vector intersect(final Ray ray) {
        final Intersection nearest = nearestIntersection(ray);

        return nearest.isIntersection() ? sumColour(ray, nearest) : miss(ray);
    }

    /**
     * Combines all colours of an intersection (ambient, local, reflected, etc.).
     *
     * @param ray the {@link Ray} which intersected.
     * @param intersection the {@link Intersection} to calculate colour for.
     * @return the colour of this {@link Intersection}.
     */
    private Vector sumColour(final Ray ray, final Intersection intersection) {
        Vector color = NONE;

        color = color.add(intersection.ambientColour().times(mAmbient));
        color = color.add(localIllumination(intersection));
        color = color.add(intersection.reflectedColour(ray, this::trace));

        return color;
    }

    /**
     * Handles what to do in the case of no {@link Intersection}.
     *
     * @param ray the {@link Ray} which has missed.
     * @return the colour of this "miss".
     */
    private Vector miss(final Ray ray) {
        return ray.isReflected() ? NONE : new Vector(mBackground);
    }

    /**
     * Finds the nearest {@link Intersection} in this scene.
     *
     * @param ray the {@link Ray} to calculate intersection for.
     * @return the nearest {@link Intersection} in this {@link Scene}.
     */
    private Intersection nearestIntersection(final Ray ray) {
        Intersection intersection = Intersection.NONE;

        for (final ISceneObject object: mObjects) {
            Intersection test = object.intersect(ray, mView);

            // Find the nearest intersection
            if (test.isIntersection() && test.compareTo(intersection) < 0) {
                intersection = test;
            }
        }

        return intersection;
    }

    /**
     * Calculates diffuse, specular, and reflected colour of an {@link Intersection}.
     *
     * @param local the {@link Intersection} to calculate colour for.
     * @return the local colour of the given {@link Intersection}.
     */
    private Vector localIllumination(final Intersection local) {
        Vector color = NONE;

        for (final Light light : mLights) {
            // Calculate shadow Ray of this light
            final Ray shadowRay = light.shadowRay(local.point());
            final Intersection lightT = light.intersect(shadowRay);
            // Check if this Ray hits any other objects in the scene
            final Intersection shadow = nearestIntersection(shadowRay);

            if (!shadow.isIntersection() || lightT.compareTo(shadow) < 0) {
                // Add diffuse and specular color if not in shadow
                color = color.add(local.diffuseColour(shadowRay, light.intensity()));
                color = color.add(local.specularColour(shadowRay, light.intensity()));
            }
        }

        return color;
    }
}
