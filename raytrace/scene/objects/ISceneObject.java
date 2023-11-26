package raytrace.scene.objects;

import Jama.Matrix;
import raytrace.scene.primitives.Intersection;
import raytrace.scene.primitives.Ray;
import raytrace.scene.primitives.Vector;

public interface ISceneObject {

    Intersection intersect(final Ray ray);

    Vector ambientColour();

    Vector diffuseColour(final Ray light, final Matrix normal, final Vector intensity);

    Vector specularColour(final Ray light, final Matrix normal, final Matrix point, final Vector intensity);
}
