package src.scene.objects;

import Jama.Matrix;
import src.scene.primitives.Intersection;
import src.scene.primitives.Ray;
import src.scene.primitives.Vector;

public interface ISceneObject {

    Intersection intersect(final Ray ray);

    Vector ambientColour();

    Vector diffuseColour(final Ray light, final Matrix point, final Vector intensity);

    Vector specularColour(final Ray light, final Matrix normal, final Matrix point, final Vector intensity);
}
