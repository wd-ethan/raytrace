package src.scene.decoders;

import src.scene.SceneBuilder;

import java.util.StringTokenizer;

public class NearPlaneDecoder extends AbstractDecoder {

    @Override
    public SceneBuilder decode(final StringTokenizer parts, final SceneBuilder sceneBuilder) {
        final float near = parseFloat(parts);
        return sceneBuilder.withNearPlane(near);
    }
}
