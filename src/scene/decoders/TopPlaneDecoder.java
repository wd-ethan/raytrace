package src.scene.decoders;

import src.scene.SceneBuilder;

import java.util.StringTokenizer;

public class TopPlaneDecoder extends AbstractDecoder {
    @Override
    public SceneBuilder decode(final StringTokenizer parts, final SceneBuilder sceneBuilder) {
        final float top = parseFloat(parts);
        return sceneBuilder.withTopPlane(top);
    }
}
