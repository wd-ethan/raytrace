package com.tracer.scene.objects;

import Jama.Matrix;
import com.tracer.primitives.ViewPort;
import com.tracer.scene.primitives.Intersection;
import com.tracer.primitives.Ray;
import com.tracer.scene.primitives.Vector;

/**
 * Defines what a {@link ISceneObject} must be able to do.
 */
public interface ISceneObject {

    Intersection intersect(final Ray ray, final ViewPort view);

    Vector ambientColour();

    Vector diffuseColour(final Ray light, final Matrix point, final Vector intensity);

    Vector specularColour(final Ray light, final Matrix normal, final Matrix point, final Vector intensity);

    Vector reflectedColour(final Vector color);
}
