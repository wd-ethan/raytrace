package src.scene.decoders;

import src.scene.SceneBuilder;

import java.util.StringTokenizer;

public class NameDecoder implements IDecoder {
    @Override
    public SceneBuilder decode(final StringTokenizer parts, final SceneBuilder sceneBuilder) {
        return sceneBuilder.withOutputName(parts.nextToken());
    }
}
