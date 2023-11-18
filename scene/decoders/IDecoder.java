package scene.decoders;

import scene.SceneBuilder;

public interface IDecoder {

    public void decode(final String[] parts, final SceneBuilder sceneBuilder);
}
