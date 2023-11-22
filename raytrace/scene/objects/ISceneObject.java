package raytrace.scene.objects;

import raytrace.scene.primitives.Intersection;
import raytrace.scene.primitives.Ray;

public interface ISceneObject {

    Intersection intersect(final Ray ray);
}
