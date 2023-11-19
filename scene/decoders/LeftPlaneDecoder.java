package scene.decoders;

import scene.SceneBuilder;

import java.util.StringTokenizer;

public class LeftPlaneDecoder extends AbstractDecoder {

    @Override
    public SceneBuilder decode(final StringTokenizer parts, final SceneBuilder sceneBuilder) {
        return sceneBuilder.withLeftPlane(parseFloat(parts));
    }
}
