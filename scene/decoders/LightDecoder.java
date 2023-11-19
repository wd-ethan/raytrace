package scene.decoders;

import scene.primitives.Light;
import scene.SceneBuilder;
import scene.primitives.Vector;

import java.util.StringTokenizer;

public class LightDecoder extends AbstractDecoder {

    @Override
    public SceneBuilder decode(final StringTokenizer parts, final SceneBuilder sceneBuilder) {
        final String name = parts.nextToken();
        final Vector position = parseVector(parts, 3);
        final Vector intensity = parseVector(parts, 3);

        return sceneBuilder.withLight(new Light(name, position, intensity));
    }
}
