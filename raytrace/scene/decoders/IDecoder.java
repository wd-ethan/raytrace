package raytrace.scene.decoders;

import raytrace.scene.SceneBuilder;

import java.util.StringTokenizer;

public interface IDecoder {

    SceneBuilder decode(final StringTokenizer parts, final SceneBuilder sceneBuilder);
}
