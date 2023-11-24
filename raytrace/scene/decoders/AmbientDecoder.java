package raytrace.scene.decoders;

import raytrace.scene.SceneBuilder;
import raytrace.scene.primitives.Vector;

import java.util.StringTokenizer;

public class AmbientDecoder extends AbstractDecoder
{
    @Override
    public SceneBuilder decode(StringTokenizer parts, SceneBuilder sceneBuilder) {
        return sceneBuilder.withAmbient(parseVector(parts, 3));
    }
}
