package src.scene.decoders;

import src.scene.SceneBuilder;

import java.util.StringTokenizer;

public class AmbientDecoder extends AbstractDecoder
{
    @Override
    public SceneBuilder decode(StringTokenizer parts, SceneBuilder sceneBuilder) {
        return sceneBuilder.withAmbient(parseVector(parts, 3));
    }
}
