package src.main.java.com.tracer.scene.objects;

import Jama.Matrix;
import src.main.java.com.tracer.scene.primitives.Intersection;
import src.main.java.com.tracer.scene.primitives.Ray;
import src.main.java.com.tracer.scene.primitives.Vector;

public interface ISceneObject {

    Intersection intersect(final Ray ray);

    Vector ambientColour();

    Vector diffuseColour(final Ray light, final Matrix point, final Vector intensity);

    Vector specularColour(final Ray light, final Matrix normal, final Matrix point, final Vector intensity);
}
