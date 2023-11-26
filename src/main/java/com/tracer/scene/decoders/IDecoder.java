package src.main.java.com.tracer.scene.decoders;

import src.main.java.com.tracer.scene.SceneBuilder;

import java.util.StringTokenizer;

public interface IDecoder {

    SceneBuilder decode(final StringTokenizer parts, final SceneBuilder sceneBuilder);
}