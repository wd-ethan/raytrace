package raytrace.scene.decoders;

import raytrace.scene.SceneBuilder;

import java.util.StringTokenizer;

public class BottomPlaneDecoder extends AbstractDecoder {

    @Override
    public SceneBuilder decode(final StringTokenizer parts, final SceneBuilder sceneBuilder) {
        final float bottom = parseFloat(parts);
        return sceneBuilder.withBottomPlane(bottom);
    }
}
