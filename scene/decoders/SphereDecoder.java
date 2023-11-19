package scene.decoders;

import scene.SceneBuilder;
import scene.primitives.Sphere;
import scene.primitives.Vector;

import java.awt.*;
import java.util.StringTokenizer;

public class SphereDecoder extends AbstractDecoder {

    @Override
    public SceneBuilder decode(final StringTokenizer parts, final SceneBuilder sceneBuilder) {
        final String name = parts.nextToken();
        final Vector position = parseVector(parts, 3);
        final Vector scale = parseVector(parts, 3);
        final Color color = parseColor(parts);
        final Vector constants = parseVector(parts, 4);
        final float specular = parseFloat(parts);

        return sceneBuilder.withSphere(new Sphere(name, position, scale, color, constants, specular));
    }
}
